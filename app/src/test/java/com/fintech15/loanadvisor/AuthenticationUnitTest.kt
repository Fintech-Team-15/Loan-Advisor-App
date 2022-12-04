package com.fintech15.loanadvisor

import com.fintech15.loanadvisor.data.AuthenticationRepository
import com.fintech15.loanadvisor.data.Result
import com.fintech15.loanadvisor.data.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AuthenticationUnitTest {
    private lateinit var authenticationRepository: AuthenticationRepository

    @Before
    fun setup() {
        authenticationRepository = FakeAuthenticationRepository()
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun loginWithEmailAndPassword() = runTest {
        assertEquals(
            true,
            authenticationRepository::class.java.interfaces.contains(AuthenticationRepository::class.java)
        )

        assertEquals(
            Result.Success(
                User(
                    email = "kitso@gmail.com",
                    isLoggedIn = true,
                    userName = "kitso@gmail.com"
                )
            ),
            authenticationRepository.loginWithEmailAndPassword("kitso@gmail.com", "123qwert")
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun registerWithEmailAndPassword() = runTest {
        assertEquals(
            Result.Success(
                User(
                    email = "kitso@gmail.com",
                    isLoggedIn = true,
                    userName = "kitso@gmail.com"
                )
            ),
            authenticationRepository.registerWithEmailAndPassword("kitso@gmail.com", "123qwert")
        )
    }

}