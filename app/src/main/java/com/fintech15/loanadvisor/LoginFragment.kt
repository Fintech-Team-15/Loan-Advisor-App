package com.fintech15.loanadvisor

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.fintech15.loanadvisor.databinding.FragmentLoginBinding
import com.fintech15.loanadvisor.utils.PASSWORD_LENGTH
import com.google.android.material.textfield.TextInputEditText

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var emailTextInput: TextInputEditText
    private lateinit var passwordTextInput: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        emailTextInput = binding.loginEmailTextInput
        passwordTextInput = binding.loginPasswordTextInput

        binding.loginButton.setOnClickListener {
            validateEmail()
            validatePassword()
        }

        binding.forgotPassword.setOnClickListener {
            Toast.makeText(context, "Implement password reset UI", Toast.LENGTH_SHORT).show()
        }

        binding.register.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegistrationFragment()
            view?.findNavController()?.navigate(action)
        }

        return binding.root
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
            emailTextInput.error = "Email cannot be empty"
        }

        if (email.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailTextInput.error = "Please enter a valid email address"
        }
    }
}