package com.fintech15.loanadvisor.utils

import java.util.regex.Pattern

object Validator {
    private const val PASSWORD_LENGTH = 6
    private val EMAIL_ADDRESS_PATTERN = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    fun isEmptyString(string: String): Boolean {
        return string.isEmpty()
    }

    fun isEmailAddress(email: String): Boolean {
        if (email.isEmpty())
            return false
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches()
    }

    fun isValidPassword(password: String): Boolean {
        if (password.isEmpty())
            return false
        return password.length >= PASSWORD_LENGTH
    }
}