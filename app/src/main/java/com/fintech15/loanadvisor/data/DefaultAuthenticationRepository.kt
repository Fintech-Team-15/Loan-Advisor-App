package com.fintech15.loanadvisor.data

class DefaultAuthenticationRepository : AuthenticationRepository {
    override fun loginWithEmailAndPassword(email: String, password: String): Result<User> {
        return Result.Success(User(email=email, isLoggedIn = true, userName = email))
    }

    override fun registerWithEmailAndPassword(email: String, password: String): Result<User> {
        return Result.Success(User(email=email, isLoggedIn = true, userName = email))
    }
}
