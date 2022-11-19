package com.fintech15.loanadvisor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.fintech15.loanadvisor.databinding.FragmentRegistrationBinding
import com.fintech15.loanadvisor.utils.Validator
import com.fintech15.loanadvisor.utils.validateEmail
import com.fintech15.loanadvisor.utils.validatePassword
import com.google.android.material.textfield.TextInputLayout

/**
 * A simple [Fragment] subclass.
 */
class RegistrationFragment : Fragment() {
    private lateinit var binding: FragmentRegistrationBinding
    private lateinit var emailTextInput: TextInputLayout
    private lateinit var passwordTextInput: TextInputLayout
    private lateinit var confirmPasswordTextInput: TextInputLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        emailTextInput = binding.registrationEmailTextInput.root
        passwordTextInput = binding.registrationPasswordTextInput.root
        confirmPasswordTextInput = binding.registrationConfirmPasswordTextInput.root

        binding.registrationButton.setOnClickListener {
            validateEmail(emailTextInput)
            validatePassword(passwordTextInput)
            validateConfirmPassword()
        }

        binding.login.setOnClickListener {
            val action = RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment()
            view?.findNavController()?.navigate(action)
        }

        return binding.root
    }

    private fun validateConfirmPassword() {
        val password = confirmPasswordTextInput.editText?.text.toString()

        if (Validator.isEmptyString(password)) confirmPasswordTextInput.error =
            "Confirm password cannot be empty" else confirmPasswordTextInput.error = ""

        if (!Validator.isValidPassword(password)) confirmPasswordTextInput.error =
            "Password should be greater than 6 characters" else confirmPasswordTextInput.error = ""
        val comparePasswordFields =
            !Validator.isEmptyString(password) && password != passwordTextInput.editText?.text.toString()
        if (!comparePasswordFields) confirmPasswordTextInput.error =
            "Confirm password should match Password" else confirmPasswordTextInput.error = ""

    }
}