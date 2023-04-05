package com.itacademy.personal_ads.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.itacademy.personal_ads.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HideAdsFragment : Fragment(R.layout.fragment_hid_ads) {

    companion object {
        fun newInstance(): HideAdsFragment {
            val fragment = HideAdsFragment()
            return fragment
        }
    }
}