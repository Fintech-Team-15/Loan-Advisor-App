package com.fintech15.loanadvisor.data

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class DefaultAuthenticationRepository(
    private val firebaseAuthSource: FirebaseAuth,
    private val ioDispatcher: CoroutineDispatcher
) :
    AuthenticationRepository {

    override suspend fun loginWithEmailAndPassword(email: String, password: String): Result<User> =
        withContext(ioDispatcher) {
            return@withContext try {
                val result = firebaseAuthSource.signInWithEmailAndPassword(email, password).await()
                val user = result.user!!
                Result.Success(
                    User(
                        email = user.email!!,
                        isLoggedIn = true,
                        userName = if (user.displayName.isNullOrEmpty()) user.email!! else ""
                    )
                )
            } catch (e: FirebaseAuthException) {
                Log.e("AUTH", e.message.toString())
                Result.Error(e)
            }
        }

    override suspend fun registerWithEmailAndPassword(
        email: String,
        password: String
    ): Result<User> = withContext(ioDispatcher) {
        return@withContext try {
            val result = firebaseAuthSource.createUserWithEmailAndPassword(email, password).await()
            val user = result.user!!
            Result.Success(
                User(
                    email = user.email!!,
                    isLoggedIn = false,
                    userName = if (user.displayName.isNullOrEmpty()) user.email!! else ""
                )
            )
        } catch (e: FirebaseAuthException) {
            Log.e("AUTH", e.message.toString())
            Result.Error(e)
        }
    }
}
