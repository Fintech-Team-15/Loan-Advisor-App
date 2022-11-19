package com.fintech15.loanadvisor

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.fintech15.loanadvisor.databinding.FragmentLoginBinding
import com.fintech15.loanadvisor.utils.Validator
import com.google.android.material.textfield.TextInputLayout

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var emailTextInput: TextInputLayout
    private lateinit var passwordTextInput: TextInputLayout

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
        val password = passwordTextInput.editText?.text.toString()

        if (Validator.isEmptyString(password)) passwordTextInput.error =
            "Password cannot be empty" else passwordTextInput.error = ""

        if (!Validator.isValidPassword(password)) passwordTextInput.error =
            "Password should be greater than 6 characters" else passwordTextInput.error = ""
    }

    private fun validateEmail() {
        val email = emailTextInput.editText?.text.toString()

        if (Validator.isEmptyString(email)) emailTextInput.error =
            "Email cannot be empty" else emailTextInput.error = ""

        if (!Validator.isEmailAddress(email)) emailTextInput.error =
            "Please enter a valid email address" else emailTextInput.error = ""
    }
}