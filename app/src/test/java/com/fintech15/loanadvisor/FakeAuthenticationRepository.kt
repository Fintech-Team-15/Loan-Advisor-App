package com.fintech15.loanadvisor

import com.fintech15.loanadvisor.data.AuthenticationRepository
import com.fintech15.loanadvisor.data.Result
import com.fintech15.loanadvisor.data.User

class FakeAuthenticationRepository : AuthenticationRepository {

    override fun loginWithEmailAndPassword(email: String, password: String): Result<User> {
        return Result.Success(User(email=email, isLoggedIn = true, userName = email))
    }

    override fun registerWithEmailAndPassword(email: String, password: String): Result<User> {
        return Result.Success(User(email=email, isLoggedIn = true, userName = email))
    }
}
