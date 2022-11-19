package com.fintech15.loanadvisor

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.fintech15.loanadvisor.databinding.FragmentRegistrationBinding
import com.fintech15.loanadvisor.utils.PASSWORD_LENGTH
import com.fintech15.loanadvisor.utils.Validator
import com.google.android.material.textfield.TextInputEditText
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




    private fun validatePassword() {
        val password = passwordTextInput.editText?.text.toString()

        if (Validator.isEmptyString(password)) passwordTextInput.error =
            "Password cannot be empty" else passwordTextInput.error = ""

        if (!Validator.isValidPassword(password)) passwordTextInput.error =
            "Password should be greater than 6 characters" else passwordTextInput.error = ""
    }

    private fun validateConfirmPassword() {
        val password = confirmPasswordTextInput.editText?.text.toString()

        if (Validator.isEmptyString(password)) confirmPasswordTextInput.error =
            "Confirm password cannot be empty" else confirmPasswordTextInput.error = ""

        if (!Validator.isValidPassword(password)) confirmPasswordTextInput.error =
            "Password should be greater than 6 characters" else confirmPasswordTextInput.error = ""

        if (password != passwordTextInput.editText?.text.toString()) {
            confirmPasswordTextInput.error = "Confirm password should match Password"
        }
    }

    private fun validateEmail() {
        val email = emailTextInput.editText?.text.toString()

        if (Validator.isEmptyString(email)) emailTextInput.error =
            "Email cannot be empty" else emailTextInput.error = ""

        if (!Validator.isEmailAddress(email)) emailTextInput.error =
            "Please enter a valid email address" else emailTextInput.error = ""
    }

}