package com.itacademy.waceplare.ui.fragment

import android.content.res.ColorStateList
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.itacademy.common.Resource
import com.itacademy.common.model.AuthResult
import com.itacademy.navigation.NavCommand
import com.itacademy.navigation.NavCommands
import com.itacademy.navigation.navigate
import com.itacademy.sign_in.presentation.AuthViewModel
import com.itacademy.waceplare.R
import com.itacademy.waceplare.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val binding by viewBinding<FragmentSplashBinding>()
    private val viewModel by viewModels<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.isAuthenticated.collect { isAuthenticated ->
                when (isAuthenticated) {
                    is Resource.Success -> {
                        view.postDelayed({
                            Log.d("AuthTEST", isAuthenticated.toString())
                            findNavController().navigate(R.id.action_splashFragment_to_tabsFragment)
                        }, 1000)

                    }
                    is Resource.Error -> {
                        Log.d("AuthTEST", isAuthenticated.toString())
                        view.postDelayed({
                            navigate(
                                NavCommand(
                                    NavCommands.DeepLink(
                                        url = Uri.parse("waceplare://login"),
                                        isSingleTop = true
                                    )
                                )
                            )
                        }, 1000)
                    }
                    is Resource.Loading -> {
                        Log.d("AuthTEST", isAuthenticated.toString())

                    }

                }
            }
        }


    }
}
