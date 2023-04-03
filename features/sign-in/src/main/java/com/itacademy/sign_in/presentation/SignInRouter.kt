package com.itacademy.sign_in.presentation

import androidx.fragment.app.Fragment

interface SignInRouter {
    fun launchMain(fragment: Fragment)
    fun launchSignUp()
}