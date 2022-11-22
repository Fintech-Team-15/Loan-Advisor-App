package com.fintech15.loanadvisor

import com.fintech15.loanadvisor.data.AuthenticationRepository
import com.fintech15.loanadvisor.data.DefaultAuthenticationRepository
import com.fintech15.loanadvisor.data.User
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AuthenticationUnitTest {
    private lateinit var authenticationRepository: AuthenticationRepository

    @Before
    fun setup() {
        authenticationRepository = DefaultAuthenticationRepository()
    }


    @Test
    fun loginWithEmailAndPassword() {
        assertEquals(
            true,
            authenticationRepository::class.java.interfaces.contains(AuthenticationRepository::class.java)
        )

        assertEquals(
            User(email = "kitso@gmail.com"),
            authenticationRepository.loginWithEmailAndPassword("kitso@gmail.com", "123qwert")!!
        )
    }

    @Test
    fun registerWithEmailAndPassword() {

        assertEquals(
            User(email = "kitso@gmail.com"),
            authenticationRepository.registerWithEmailAndPassword("kitso@gmail.com", "123qwert")!!
        )
    }

}