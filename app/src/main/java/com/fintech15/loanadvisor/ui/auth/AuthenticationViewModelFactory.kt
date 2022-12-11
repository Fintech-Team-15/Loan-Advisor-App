package com.fintech15.loanadvisor.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fintech15.loanadvisor.AuthenticationViewModel
import com.fintech15.loanadvisor.data.AuthenticationRepository

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class AuthenticationViewModelFactory(private val authenticationRepository: AuthenticationRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthenticationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AuthenticationViewModel(authenticationRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}