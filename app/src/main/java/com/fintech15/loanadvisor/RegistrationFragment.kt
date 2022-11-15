package com.fintech15.loanadvisor

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.fintech15.loanadvisor.databinding.FragmentRegistrationBinding
import com.fintech15.loanadvisor.utils.PASSWORD_LENGTH
import com.google.android.material.textfield.TextInputEditText

/**
 * A simple [Fragment] subclass.
 */
class RegistrationFragment : Fragment() {
    private lateinit var binding: FragmentRegistrationBinding
    private lateinit var emailTextInput: TextInputEditText
    private lateinit var passwordTextInput: TextInputEditText
    private lateinit var confirmPasswordTextInput: TextInputEditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        emailTextInput = binding.registrationEmailTextInput
        passwordTextInput = binding.registrationPasswordTextInput
        confirmPasswordTextInput = binding.registrationConfirmPasswordTextInput

        binding.registrationButton.setOnClickListener {
            validateEmail()
            validatePassword()
            validateConfirmPassword()
        }

        binding.login.setOnClickListener {
            val action = RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment()
            view?.findNavController()?.navigate(action)
        }

        return binding.root
    }

    private fun validateConfirmPassword() {
        val password = confirmPasswordTextInput.text.toString()

        if (password.isEmpty()) {
            confirmPasswordTextInput.error = "Confirm Password cannot be empty"
        }

        if (password.length < PASSWORD_LENGTH) {
            confirmPasswordTextInput.error = "Confirm Password should be greater than 6 characters"
        }
        if (!password.equals(passwordTextInput.text)) {
            confirmPasswordTextInput.error = "Confirm password should match Password"
        }
    }

    private fun validatePassword() {
        val password = passwordTextInput.text.toString()

        if (password.isEmpty()) {
            passwordTextInput.error = "Password cannot be empty"
        }

        if (password.length < PASSWORD_LENGTH) {
            passwordTextInput.error = "Password should be greater than 6 characters"
        }
    }

    private fun validateEmail() {
        val email = emailTextInput.text.toString()
        if (email.isEmpty()) {
            emailTextInput.error = "Email cadnot be empty"
        }

        if (email.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailTextInput.error = "Please enter a valid email address"
        }
    }
}