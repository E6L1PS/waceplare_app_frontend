package com.itacademy.waceplare.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.itacademy.waceplare.R
import com.itacademy.data.model.auth.AuthenticationRequest
import com.itacademy.waceplare.databinding.FragmentSignInBinding
import com.itacademy.waceplare.ui.view.AuthViewModel
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
                    if (isAuthenticated) {
                        Log.d("isAuthenticated", "navvv")
                        findNavController().navigate(R.id.action_signInFragment_to_tabsFragment)
                    }
                }
            }


        }


    }
}