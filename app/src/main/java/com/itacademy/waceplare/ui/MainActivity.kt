package com.itacademy.waceplare.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.itacademy.navigation.NavCommand
import com.itacademy.navigation.NavCommands
import com.itacademy.navigation.NavigationProvider
import com.itacademy.waceplare.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationProvider {

    private val navController: NavController
        get() = findNavController(R.id.fragmentContainer)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun launch(navCommand: NavCommand) {
        when (val target = navCommand.target) {
            is NavCommands.DeepLink -> openDeepLink(
                url = target.url,
                isModal = target.isModal,
                isSingleTop = target.isSingleTop
            )
            is NavCommands.Browser -> openBrowser(url = target.url)
        }
    }

    private fun openDeepLink(url: Uri, isModal: Boolean, isSingleTop: Boolean) {
        val navOptions = if (isModal) {
            NavOptions.Builder()
                .setEnterAnim(androidx.navigation.ui.R.anim.nav_default_enter_anim)
                .setExitAnim(androidx.navigation.ui.R.anim.nav_default_exit_anim)
                .setPopEnterAnim(androidx.navigation.ui.R.anim.nav_default_pop_enter_anim)
                .setPopExitAnim(androidx.navigation.ui.R.anim.nav_default_pop_exit_anim)
                .setLaunchSingleTop(isSingleTop)
                .setPopUpTo(if (isSingleTop) R.id.main_nav_graph else -1, inclusive = isSingleTop)
                .build()
        } else {
            NavOptions.Builder()
                .setEnterAnim(androidx.navigation.ui.R.anim.nav_default_enter_anim)
                .setExitAnim(androidx.navigation.ui.R.anim.nav_default_exit_anim)
                .setPopEnterAnim(androidx.navigation.ui.R.anim.nav_default_pop_enter_anim)
                .setPopExitAnim(androidx.navigation.ui.R.anim.nav_default_pop_exit_anim)
                .setLaunchSingleTop(isSingleTop)
                .setPopUpTo(if (isSingleTop) R.id.main_nav_graph else -1, inclusive = isSingleTop)
                .build()
        }

        navController.navigate(url, navOptions)
    }

    private fun openBrowser(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        browserIntent.setPackage("com.android.chrome")
        try {
            this.startActivity(browserIntent)
        } catch (ex: ActivityNotFoundException) {
            browserIntent.setPackage(null)
            this.startActivity(browserIntent)
        }
    }


}