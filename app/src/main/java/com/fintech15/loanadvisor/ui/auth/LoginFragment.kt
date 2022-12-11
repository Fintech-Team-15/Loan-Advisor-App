package com.fintech15.loanadvisor.ui.auth

import android.os.Bundle
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
import com.fintech15.loanadvisor.databinding.FragmentLoginBinding
import com.fintech15.loanadvisor.utils.validateEmail
import com.fintech15.loanadvisor.utils.validatePassword
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var emailTextInput: TextInputLayout
    private lateinit var passwordTextInput: TextInputLayout
    private val viewModel: AuthenticationViewModel by activityViewModels {
        AuthenticationViewModelFactory(
            (activity?.application as LoanAdvisorApplication).authenticationRepository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        emailTextInput = binding.loginEmailTextInput.root
        passwordTextInput = binding.loginPasswordTextInput.root

        return binding.root
    }

    private fun login() {
        if (!canLogin()) {
            return
        }

        viewModel.login(
            emailTextInput.editText?.text.toString(),
            passwordTextInput.editText?.text.toString()
        )
    }

    private fun canLogin(): Boolean {
        return emailTextInput.error.isNullOrEmpty() && passwordTextInput.error.isNullOrEmpty()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    if (it.isLoading) {
                        binding.loginButton.isEnabled = false
                        binding.loginButton.text = getString(R.string.login_checking_credentials)
                    } else if (it.isLoggedIn) {
                        binding.loginButton.isEnabled = true
                        binding.loginButton.text = getString(R.string.login)
                        Toast.makeText(context, "Loggin Successful", Toast.LENGTH_SHORT).show()
                        val action =
                            LoginFragmentDirections.actionLoginFragmentToLoanCalculatorFragment()
                        view.findNavController().navigate(action)
                    } else if (it.errorText.isNotEmpty()) {
                        binding.loginButton.isEnabled = true
                        binding.loginButton.text = getString(R.string.login)
                        Toast.makeText(context, it.errorText, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        binding.loginButton.setOnClickListener {
            validateEmail(emailTextInput)
            validatePassword(passwordTextInput)
            login()
        }

        binding.forgotPassword.setOnClickListener {
            Toast.makeText(context, "Implement password reset UI", Toast.LENGTH_SHORT).show()
        }

        binding.register.setOnClickListener {
            navigateToRegistrationScreen()
        }


    }

    private fun navigateToRegistrationScreen() {
        val action = LoginFragmentDirections.actionLoginFragmentToRegistrationFragment()
        view?.findNavController()?.navigate(action)
    }
}