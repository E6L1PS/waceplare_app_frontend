package com.itacademy.personal_ads.presentation.create_ad_screens

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.itacademy.navigation.NavCommand
import com.itacademy.navigation.NavCommands
import com.itacademy.navigation.navigate
import com.itacademy.personal_ads.R
import com.itacademy.personal_ads.databinding.FragmentCreateAdBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateAdFragment : Fragment(R.layout.fragment_create_ad) {

    private val binding by viewBinding<FragmentCreateAdBinding>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            navigate(
                NavCommand(
                    NavCommands.DeepLink(
                        url = Uri.parse("waceplare://naming_ad")
                    )
                )
            )
        }

    }
}