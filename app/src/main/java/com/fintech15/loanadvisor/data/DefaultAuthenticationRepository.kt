package com.fintech15.loanadvisor.data

class DefaultAuthenticationRepository : AuthenticationRepository {
    override fun loginWithEmailAndPassword(email: String, password: String): User? {
        return User(email=email)
    }

    override fun registerWithEmailAndPassword(email: String, password: String): User? {
        return User(email=email)
    }
}
