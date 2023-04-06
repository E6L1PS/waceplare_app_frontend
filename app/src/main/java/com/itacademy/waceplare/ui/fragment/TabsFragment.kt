package com.itacademy.waceplare.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavHost
import androidx.navigation.ui.NavigationUI.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.itacademy.waceplare.R
import com.itacademy.waceplare.databinding.FragmentTabsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TabsFragment : Fragment(R.layout.fragment_tabs) {

    private val binding by viewBinding<FragmentTabsBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHost = childFragmentManager.findFragmentById(R.id.fcv_tabs) as NavHost
        val navController = navHost.navController

        setupWithNavController(binding.bnv, navController)


    }

}