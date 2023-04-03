package com.itacademy.favorites.presentation

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.itacademy.favorites.R
import com.itacademy.favorites.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private val binding by viewBinding<FragmentFavoritesBinding>()

}