package com.itacademy.personal_ads.presentation.create_ad_screens

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.itacademy.personal_ads.R
import com.itacademy.personal_ads.databinding.FragmentNamingAdBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NamingAdFragment : Fragment(R.layout.fragment_naming_ad) {


    private val binding by viewBinding<FragmentNamingAdBinding>()


}