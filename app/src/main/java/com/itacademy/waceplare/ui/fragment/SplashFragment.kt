package com.itacademy.waceplare.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.itacademy.waceplare.R
import com.itacademy.waceplare.databinding.FragmentSplashBinding
import com.itacademy.waceplare.ui.MainActivity
import com.itacademy.waceplare.ui.MainActivityArgs
import com.itacademy.waceplare.ui.view.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val binding by viewBinding<FragmentSplashBinding>()
    private val viewModel by viewModels<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("auth!!!", "1")
        lifecycleScope.launchWhenStarted {
            viewModel.isAuthenticated.collect { isAuthenticated ->
                launchMainScreen(isAuthenticated)
            }
        }
            //launchMainScreen(viewModel.isAuthenticated.value) // TODO create a functional signIn
    }
    private fun launchMainScreen(isSignedIn: Boolean) {
        val intent = Intent(requireContext(), MainActivity::class.java)

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

        val args = MainActivityArgs(isSignedIn)
        intent.putExtra("isSignedIn", args.toBundle())
        startActivity(intent)
    }

}