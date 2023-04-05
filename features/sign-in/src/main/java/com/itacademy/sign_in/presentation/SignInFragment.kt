package com.itacademy.sign_in.presentation

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.itacademy.common.Resource
import com.itacademy.common.model.AuthResult
import com.itacademy.navigation.NavCommand
import com.itacademy.navigation.NavCommands
import com.itacademy.navigation.navigate
import com.itacademy.sign_in.R
import com.itacademy.sign_in.databinding.FragmentSignInBinding
import com.itacademy.sign_in.domain.model.AuthenticationRequest
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private val binding by viewBinding<FragmentSignInBinding>()
    private val viewModel by viewModels<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnEnter.setOnClickListener {
                viewModel.signIn(
                    AuthenticationRequest(
                        tiEtEmail.text.toString(),
                        tiEtPassword.text.toString()
                    )
                )
            }

            lifecycleScope.launchWhenStarted {
                viewModel.isAuthenticated.collect { isAuthenticated ->
                    when (isAuthenticated) {
                        is Resource.Success -> {
                            navigate(
                                NavCommand(
                                    NavCommands.DeepLink(
                                        url = Uri.parse("waceplare://main"),
                                        isModal = true,
                                        isSingleTop = true
                                    )
                                )
                            )
                        }
                        is Resource.Error -> {

                        }
                        is Resource.Loading -> {

                        }

                    }
                }
            }
        }
    }


}
