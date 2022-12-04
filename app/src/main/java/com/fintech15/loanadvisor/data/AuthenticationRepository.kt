package com.fintech15.loanadvisor.data

interface AuthenticationRepository {
    suspend fun loginWithEmailAndPassword(email: String, password: String): Result<User>
    suspend fun registerWithEmailAndPassword(email: String, password: String): Result<User>
}
