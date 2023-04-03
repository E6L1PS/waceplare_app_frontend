package com.itacademy.waceplare.glue.signin

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.itacademy.sign_in.presentation.SignInRouter
import com.itacademy.waceplare.R
import javax.inject.Inject

class AdapterSignInRoute @Inject constructor(
) : SignInRouter{
    override fun launchMain(fragment: Fragment) {
        val navController = fragment.findNavController()
        navController.navigate(R.id.action_signInFragment_to_tabsFragment)
    }

    override fun launchSignUp() {
        TODO("Not yet implemented")
    }
}
