package com.fintech15.loanadvisor.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultAuthenticationRepository(
    private val firebaseAuthSource: FirebaseAuth,
    private val ioDispatcher: CoroutineDispatcher
) :
    AuthenticationRepository {

    override suspend fun loginWithEmailAndPassword(email: String, password: String): Result<User> =
        withContext(ioDispatcher) {
            return@withContext try {
                val auth = firebaseAuthSource.signInWithEmailAndPassword(email, password)
                Result.Success(
                    User(
                        email = auth.result?.user?.email!!,
                        isLoggedIn = true,
                        userName = auth.result.additionalUserInfo?.username!!
                    )
                )
            } catch (e: FirebaseAuthException) {
                Result.Error(e)
            }
        }

    override suspend fun registerWithEmailAndPassword(
        email: String,
        password: String
    ): Result<User> = withContext(ioDispatcher) {
        return@withContext try {
            val auth = firebaseAuthSource.signInWithEmailAndPassword(email, password)
            Result.Success(
                User(
                    email = auth.result?.user?.email!!,
                    isLoggedIn = true,
                    userName = auth.result.additionalUserInfo?.username!!
                )
            )
        } catch (e: FirebaseAuthException) {
            Result.Error(e)
        }
    }
}
