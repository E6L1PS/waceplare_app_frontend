package com.itacademy.personal_ads.presentation.create_ad_screens

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.itacademy.navigation.NavCommand
import com.itacademy.navigation.NavCommands
import com.itacademy.navigation.navigate
import com.itacademy.personal_ads.R
import com.itacademy.personal_ads.databinding.FragmentCreateAdBinding
import com.itacademy.personal_ads.domain.model.TypeAd
import com.itacademy.personal_ads.presentation.create_ad_screens.adapter.OnTypeAdClickListener
import com.itacademy.personal_ads.presentation.create_ad_screens.adapter.TypeAdAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateAdFragment : Fragment(R.layout.fragment_create_ad) {

    private val binding by viewBinding<FragmentCreateAdBinding>()
    private lateinit var typeAdAdapter: TypeAdAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        typeAdAdapter = TypeAdAdapter(object : OnTypeAdClickListener {
            override fun onTypeAdClick(typeAd: TypeAd) {
                when (typeAd) {
                    TypeAd.STUFF -> {
                        navigate(
                            NavCommand(
                                NavCommands.DeepLink(
                                    url = Uri.parse("waceplare://state_ad")
                                )
                            )
                        )
                    }
                    else -> {
                        navigate(
                            NavCommand(
                                NavCommands.DeepLink(
                                    url = Uri.parse("waceplare://naming_ad/$typeAd")
                                )
                            )
                        )
                    }
                }
            }

        })

        binding.rvTypes.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = typeAdAdapter
        }

    }


}