package com.itacademy.profile.presentation

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.itacademy.common.Resource
import com.itacademy.navigation.NavCommand
import com.itacademy.navigation.NavCommands
import com.itacademy.navigation.navigate
import com.itacademy.profile.R
import com.itacademy.profile.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val binding by viewBinding<FragmentProfileBinding>()
    private val viewModel by viewModels<ProfileViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.isAuthenticated.collect { isAuthenticated ->
                when (isAuthenticated) {
                    is Resource.Success -> {
                        with(binding) {
                            btnSignIn.visibility = View.GONE
                            btnLogout.visibility = View.VISIBLE
                            Log.d("navigate to sign fragment", "logout")
                            btnLogout.setOnClickListener {
                                Log.d("navigate to sign fragment", "btn")
                                viewModel.logOut()
                            }
                        }
                    }

                    is Resource.Loading -> {
                        with(binding) {
                            btnSignIn.visibility = View.GONE
                            btnLogout.visibility = View.GONE
                        }
                        Log.d("navigate to sign fragment", "load")
                    }

                    is Resource.Error -> {
                        with(binding) {
                            btnSignIn.visibility = View.VISIBLE
                            btnLogout.visibility = View.GONE
                            btnSignIn.setOnClickListener {
                                Log.d("navigate to sign fragment", "nav")
                                navigate(
                                    NavCommand(
                                        NavCommands.DeepLink(
                                            url = Uri.parse("waceplare://login"),
                                            isSingleTop = true
                                        )
                                    )
                                )
                            }
                        }
                    }

                }
            }


        }

    }
}