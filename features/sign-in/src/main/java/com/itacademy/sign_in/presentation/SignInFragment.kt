package com.itacademy.sign_in.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.itacademy.sign_in.R
import com.itacademy.sign_in.databinding.FragmentSignInBinding
import com.itacademy.sign_in.domain.model.AuthenticationRequest
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SignInFragment @Inject constructor(private val router: SignInRouter) : Fragment(R.layout.fragment_sign_in) {

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
                        router.launchMain(this@SignInFragment)
                        //findNavController().navigate(R.id.action_signInFragment_to_tabsFragment)
                    }
                }
            }


        }


    }
}