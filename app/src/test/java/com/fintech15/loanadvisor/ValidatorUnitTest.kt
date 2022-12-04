package com.fintech15.loanadvisor

import com.fintech15.loanadvisor.utils.Validator
import org.junit.Assert.*
import org.junit.Test


class ValidatorUnitTest {
    @Test
    fun validateEmptyString() {
        assertTrue(Validator.isEmptyString(""))
        assertFalse(Validator.isEmptyString("u"))
    }

    @Test
    fun validatePassword() {
        assertEquals(false, Validator.isValidPassword(""))
        assertEquals(false, Validator.isValidPassword("12345"))
        assertEquals(true, Validator.isValidPassword("123456"))
    }

    @Test
    fun validateEmailString() {
        assertFalse(Validator.isEmailAddress(""))
        assertEquals(true, Validator.isEmailAddress("kitso@gmail.com"))
        assertEquals(false, Validator.isEmailAddress("kitsogmail.com"))
    }
}