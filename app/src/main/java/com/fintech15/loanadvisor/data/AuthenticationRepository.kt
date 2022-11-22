package com.fintech15.loanadvisor.data

interface AuthenticationRepository {
    fun loginWithEmailAndPassword(email: String, password: String): Result<User>
    fun registerWithEmailAndPassword(email: String, password: String): Result<User>
}
