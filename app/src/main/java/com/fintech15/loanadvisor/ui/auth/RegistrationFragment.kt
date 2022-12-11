package com.fintech15.loanadvisor.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.fintech15.loanadvisor.AuthenticationViewModel
import com.fintech15.loanadvisor.LoanAdvisorApplication
import com.fintech15.loanadvisor.R
import com.fintech15.loanadvisor.databinding.FragmentRegistrationBinding
import com.fintech15.loanadvisor.utils.Validator
import com.fintech15.loanadvisor.utils.validateEmail
import com.fintech15.loanadvisor.utils.validatePassword
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class RegistrationFragment : Fragment() {
    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AuthenticationViewModel by activityViewModels {
        AuthenticationViewModelFactory(
            (activity?.application as LoanAdvisorApplication).authenticationRepository
        )
    }
    private lateinit var emailTextInput: TextInputLayout
    private lateinit var passwordTextInput: TextInputLayout
    private lateinit var confirmPasswordTextInput: TextInputLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        emailTextInput = binding.registrationEmailTextInput.root
        passwordTextInput = binding.registrationPasswordTextInput.root
        confirmPasswordTextInput = binding.registrationConfirmPasswordTextInput.root

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registrationButton.setOnClickListener {
            validateEmail(emailTextInput)
            validatePassword(passwordTextInput)
            validateConfirmPassword()
            register()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    if (it.isLoading) {
                        binding.registrationButton.isEnabled = false
                        binding.registrationButton.text =
                            getString(R.string.registration_registering_user_text)
                    } else if (it.isLoggedIn) {
                        registrationLoadingStateReset()
                        Toast.makeText(context, "Registration Successful", Toast.LENGTH_SHORT)
                            .show()
                        val action =
                            RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment()
                        view.findNavController().navigate(action)
                    } else if (it.errorText.isNotEmpty()) {
                        registrationLoadingStateReset()
                        Toast.makeText(context, it.errorText, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        binding.login.setOnClickListener {
            navigateToLoginScreen()
        }
    }

    private fun registrationLoadingStateReset() {
        binding.registrationButton.isEnabled = true
        binding.registrationButton.text = getString(R.string.register)
    }

    private fun register() {
        if (!canRegister()) {
            return
        }

        viewModel.register(
            emailTextInput.editText?.text.toString(),
            passwordTextInput.editText?.text.toString()
        )
    }

    private fun canRegister(): Boolean {
        return emailTextInput.error.isNullOrEmpty() && passwordTextInput.error.isNullOrEmpty() &&
                confirmPasswordTextInput.error.isNullOrEmpty()
    }

    private fun validateConfirmPassword() {
        val confirmPassword = confirmPasswordTextInput.editText?.text.toString()
        val password = passwordTextInput.editText?.text.toString()

        if (Validator.isEmptyString(confirmPassword)) confirmPasswordTextInput.error =
            "Confirm password cannot be empty" else confirmPasswordTextInput.error = ""
        if (!Validator.isValidPassword(confirmPassword)) confirmPasswordTextInput.error =
            "Password should be greater than 6 characters" else confirmPasswordTextInput.error = ""
        val passwordAndConfirmPassword =
            confirmPassword.lowercase(Locale.getDefault()) == password.lowercase(Locale.getDefault())
        val comparePasswordFields =
            !Validator.isEmptyString(confirmPassword) && passwordAndConfirmPassword
        Log.e("AUTH", Validator.isEmptyString(confirmPassword).toString())
        Log.e("AUTH", passwordAndConfirmPassword.toString())
        if (!comparePasswordFields) confirmPasswordTextInput.error =
            "Confirm password should match Password" else confirmPasswordTextInput.error = ""
    }

    private fun navigateToLoginScreen() {
        val action = RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment()
        view?.findNavController()?.navigate(action)
    }
}