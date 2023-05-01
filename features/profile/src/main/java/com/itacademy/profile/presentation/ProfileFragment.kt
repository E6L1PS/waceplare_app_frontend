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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val binding by viewBinding<FragmentProfileBinding>()
    private val viewModel by viewModels<ProfileViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.userInfo.onEach {
                when (it) {
                    is Resource.Error -> {
                        binding.infoPerson.clInfo.visibility = View.GONE
                    }
                    is Resource.Loading -> {
                        binding.infoPerson.clInfo.visibility = View.GONE
                    }
                    is Resource.Success -> {
                        val userInfo = it.data
                        if (userInfo != null) {
                            with(binding.infoPerson) {
                                clInfo.visibility = View.VISIBLE

                                tvFullName.text = userInfo.firstname + " " + userInfo.lastname
                                tvId.text = "Номер профиля " + userInfo.id.toString()
                                tvEmail.text = userInfo.email
                                tvRating.text = userInfo.rating.toString()
                                tvCalendar.text = userInfo.dateOfCreated
                                tvLocation.text = "Moscow"
                            }

                        }
                    }
                }
            }.collect()
        }


        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isAuthenticated.collect { isAuthenticated ->
                when (isAuthenticated) {
                    is Resource.Success -> {
                        with(binding) {
                            btnSignIn.visibility = View.GONE
                            infoPerson.clInfo.visibility = View.VISIBLE
                            btnLogout.setOnClickListener {
                                viewModel.logOut()
                            }
                        }
                    }

                    is Resource.Loading -> {
                        with(binding) {
                            btnSignIn.visibility = View.GONE
                            infoPerson.clInfo.visibility = View.GONE
                        }
                    }

                    is Resource.Error -> {
                        with(binding) {
                            btnSignIn.visibility = View.VISIBLE
                            infoPerson.clInfo.visibility = View.GONE
                            btnLogout.visibility = View.GONE
                            btnSignIn.setOnClickListener {
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