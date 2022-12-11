package com.fintech15.loanadvisor

import android.app.Application
import com.fintech15.loanadvisor.data.AuthenticationRepository
import com.fintech15.loanadvisor.data.DefaultAuthenticationRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers

class LoanAdvisorApplication : Application() {
    val authenticationRepository: AuthenticationRepository by lazy {
        DefaultAuthenticationRepository(Firebase.auth, Dispatchers.IO)
    }
}