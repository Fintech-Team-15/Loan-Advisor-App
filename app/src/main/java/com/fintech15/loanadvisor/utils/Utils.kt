package com.fintech15.loanadvisor.utils

import com.google.android.material.textfield.TextInputLayout

fun validatePassword(passwordTextInputLayout: TextInputLayout) {
    val password = passwordTextInputLayout.editText?.text.toString()

    if (Validator.isEmptyString(password)) passwordTextInputLayout.error =
        "Password cannot be empty" else passwordTextInputLayout.error = ""

    if (!Validator.isValidPassword(password)) passwordTextInputLayout.error =
        "Password should be greater than 6 characters" else passwordTextInputLayout.error = ""
}


fun validateEmail(emailTextInputLayout: TextInputLayout) {
    val email = emailTextInputLayout.editText?.text.toString()

    if (Validator.isEmptyString(email)) emailTextInputLayout.error =
        "Email cannot be empty" else emailTextInputLayout.error = ""

    if (!Validator.isEmailAddress(email)) emailTextInputLayout.error =
        "Please enter a valid email address" else emailTextInputLayout.error = ""
}
