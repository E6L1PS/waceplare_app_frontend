package com.itacademy.ads.presentation

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.itacademy.ads.R
import com.itacademy.ads.databinding.FragmentFavoritesBinding
import com.itacademy.common.Resource
import com.itacademy.navigation.NavCommand
import com.itacademy.navigation.NavCommands
import com.itacademy.navigation.navigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorites), MenuProvider {

    private val binding by viewBinding<FragmentFavoritesBinding>()
    private val viewModel by viewModels<FavoritesViewModel>()

    private val menuHost: MenuHost
        get() = requireActivity()
    private lateinit var favoritesAdapter: AdsAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        setupRV()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.favorites.onEach {
                when (it) {
                    is Resource.Success -> {
                        favoritesAdapter.differ.submitList(it.data)
                    }

                    is Resource.Loading -> {

                    }

                    is Resource.Error -> {
                        Toast.makeText(requireContext(), "Error favorites", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }.collect()
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavorites()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.favorites_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.action_delete_all -> {
                viewModel.deleteFavorites()
                Toast.makeText(requireContext(), "action_delete_all", Toast.LENGTH_LONG).show()
            }

            R.id.action_delete_inactive -> {
                viewModel.deleteInactiveFavorites()
                Toast.makeText(requireContext(), "action_delete_inactive", Toast.LENGTH_LONG).show()
            }
        }

        return true
    }

    private fun setupRV() {
        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        dividerItemDecoration.setDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                com.itacademy.theme.R.drawable.default_divider
            )!!
        )

        favoritesAdapter = AdsAdapter(object : AdOnClickListener {
            override fun adSelect(adId: Long) {
                Log.d("navigateToAboutAd", adId.toString())
                navigate(
                    NavCommand(
                        NavCommands.DeepLink(
                            url = Uri.parse("waceplare://about/$adId")
                        )
                    )
                )
            }

            override fun addFavorite(adId: Long) {
                viewModel.addFavorite(adId)
            }

            override fun deleteFavorite(adId: Long) {
                viewModel.deleteFavorite(adId)
            }

        }, this@FavoritesFragment)

        binding.rvFavorites.apply {
            addItemDecoration(dividerItemDecoration)
            layoutManager = LinearLayoutManager(activity)
            adapter = favoritesAdapter
        }

    }

}