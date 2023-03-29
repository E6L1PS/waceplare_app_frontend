package com.itacademy.waceplare.ui.fragment

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.itacademy.waceplare.R
import com.itacademy.waceplare.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private val binding by viewBinding<FragmentFavoritesBinding>()

}