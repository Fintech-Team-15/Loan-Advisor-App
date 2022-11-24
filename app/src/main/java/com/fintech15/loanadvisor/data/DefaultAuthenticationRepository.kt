package com.fintech15.loanadvisor.data

import com.google.firebase.auth.FirebaseAuth

class DefaultAuthenticationRepository(val firebaseAuthSource: FirebaseAuth) : AuthenticationRepository {

    override fun loginWithEmailAndPassword(email: String, password: String): Result<User> {
        return try {
            val auth = firebaseAuthSource.signInWithEmailAndPassword(email, password)
            Result.Success(User(email=auth.result?.user?.email!!, isLoggedIn = true, userName = auth.result.additionalUserInfo?.username!!))
        } catch (e: Exception) {
           Result.Error(e)
        }
    }

    override fun registerWithEmailAndPassword(email: String, password: String): Result<User> {
        return try {
            val auth = firebaseAuthSource.signInWithEmailAndPassword(email, password)
            Result.Success(User(email=auth.result?.user?.email!!, isLoggedIn = true, userName = auth.result.additionalUserInfo?.username!!))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
