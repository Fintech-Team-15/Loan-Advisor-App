package com.fintech15.loanadvisor

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testEmptyInput() {
        val loginButton =
            onView(withId(R.id.login_button)).check(matches(isClickable()))
        loginButton.perform(click())
    }

    @Test
    fun testEmailInput() {
        val emailTextInput =
            onView(withId(R.id.login_email_text_input)).check(matches(isDisplayed()))
        emailTextInput.perform(typeText("kitso@gmail.com"))
            .check(matches(withText("kitso@gmail.com")))
    }

    @Test
    fun testPasswordInput() {
        val passwordTextInput =
            onView(withId(R.id.login_password_text_input)).check(matches(isDisplayed()))
                .perform(
                    click()
                )
        passwordTextInput.perform(typeText("1234567"))
            .check(matches(withText("1234567")))
    }

    @Test
    fun testInvalidInput() {
        val emailTextInput =
            onView(withId(R.id.login_email_text_input)).perform(click())
        emailTextInput.perform(typeText("kitso"))
            .check(matches(withText("kitso")))
        val passwordTextInput =
            onView(withId(R.id.login_password_text_input)).perform(
                click()
            )
        passwordTextInput.perform(typeText("1234"))
            .check(matches(withText("1234")))

        val loginButton =
            onView(withId(R.id.login_button)).check(matches(isClickable()))
        loginButton.perform(click())

        onView(withId(R.id.login_password_text_input)).check(
            matches(
                hasErrorText("Please enter a valid email address")
            )
        )

        onView(
            allOf(
                withId(R.id.login_password_text_input),
                hasErrorText("Password should be greater than 6 characters")
            )
        ).check(matches(isDisplayed()))
    }
}