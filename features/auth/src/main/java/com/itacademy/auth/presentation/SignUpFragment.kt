package com.itacademy.auth.presentation

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.itacademy.auth.R
import com.itacademy.auth.databinding.FragmentSignUpBinding
import com.itacademy.common.Resource
import com.itacademy.navigation.NavCommand
import com.itacademy.navigation.NavCommands
import com.itacademy.navigation.navigate
import com.itacademy.common.model.RegisterRequest
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private val binding by viewBinding<FragmentSignUpBinding>()
    private val viewModel by viewModels<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnReg.setOnClickListener {
                val password = tiEtPassword.text.toString()
                val repeatPassword = tiEtRepeatPassword.text.toString()

                if (password == repeatPassword) {
                    viewModel.signUp(
                        RegisterRequest(
                            email = tiEtEmail.text.toString(),
                            password = password,
                            firstname = tiEtName.text.toString(),
                            lastname = tiEtLastName.text.toString()
                        )
                    )
                } else {
                    // Показать сообщение об ошибке пользователю
                    Toast.makeText(requireContext(), "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                }
            }
        }


        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isAuthenticated.collect { isAuthenticated ->
                when (isAuthenticated) {
                    is Resource.Success -> {
                        navigate(
                            NavCommand(
                                NavCommands.DeepLink(
                                    url = Uri.parse("waceplare://main"),
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