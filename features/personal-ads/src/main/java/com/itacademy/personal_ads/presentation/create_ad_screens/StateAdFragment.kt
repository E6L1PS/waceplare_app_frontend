package com.itacademy.personal_ads.presentation.create_ad_screens

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.itacademy.navigation.NavCommand
import com.itacademy.navigation.NavCommands
import com.itacademy.personal_ads.R
import com.itacademy.personal_ads.databinding.FragmentStateAdBinding
import com.itacademy.personal_ads.domain.model.StateAd
import com.itacademy.personal_ads.domain.model.TypeAd
import com.itacademy.personal_ads.presentation.create_ad_screens.adapter.OnStateAdClickListener
import com.itacademy.personal_ads.presentation.create_ad_screens.adapter.StateAdAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StateAdFragment : Fragment(R.layout.fragment_state_ad) {

    private val binding by viewBinding<FragmentStateAdBinding>()
    private lateinit var stateAdAdapter: StateAdAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        stateAdAdapter = StateAdAdapter(object : OnStateAdClickListener {
            override fun onStateAdClick(stateAd: StateAd) {
                NavCommand(
                    NavCommands.DeepLink(
                        url = Uri.parse("waceplare://naming_ad/$stateAd")
                    )
                )
            }
        } )

        binding.rvTypes.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = stateAdAdapter
        }

    }

}