package com.fintech15.loanadvisor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.fintech15.loanadvisor.databinding.FragmentLoginBinding
import com.fintech15.loanadvisor.utils.validateEmail
import com.fintech15.loanadvisor.utils.validatePassword
import com.google.android.material.textfield.TextInputLayout

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var emailTextInput: TextInputLayout
    private lateinit var passwordTextInput: TextInputLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        emailTextInput = binding.loginEmailTextInput.root
        passwordTextInput = binding.loginPasswordTextInput.root

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginButton.setOnClickListener {
            validateEmail(emailTextInput)
            validatePassword(passwordTextInput)
            navigateToHomeScreen()
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

    private fun navigateToHomeScreen() {
        if (emailTextInput.error.isNullOrEmpty() && passwordTextInput.error.isNullOrEmpty()) {
            val action = LoginFragmentDirections.actionLoginFragmentToLoanCalculatorFragment()
            view?.findNavController()?.navigate(action)
        }
    }

}