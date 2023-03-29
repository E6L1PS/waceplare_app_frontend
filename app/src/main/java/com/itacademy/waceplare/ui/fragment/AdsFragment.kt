package com.itacademy.waceplare.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.itacademy.waceplare.R
import com.itacademy.waceplare.databinding.FragmentAdsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdsFragment : Fragment(R.layout.fragment_ads) {

    private val binding by viewBinding<FragmentAdsBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}