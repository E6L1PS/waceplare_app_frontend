package com.itacademy.waceplare.ui.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.itacademy.waceplare.R
import com.itacademy.waceplare.databinding.FragmentSearchBinding
import com.itacademy.waceplare.ui.adapter.AdsAdapter
import com.itacademy.waceplare.ui.view.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search), SearchView.OnQueryTextListener,
    MenuProvider {

    private lateinit var adsAdapter: AdsAdapter
    private val viewModel by viewModels<SearchViewModel>()
    private val binding by viewBinding<FragmentSearchBinding>()
    private val menuHost: MenuHost get() = requireActivity()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRV()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        lifecycleScope.launchWhenStarted {
            viewModel.ads
                .onEach { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            val data = resource.data

                            if (data.isNullOrEmpty()) {
                                binding.tvEmptyList.visibility = View.VISIBLE
                            } else {
                                binding.tvEmptyList.visibility = View.GONE
                            }

                            adsAdapter.differ.submitList(data)
                            binding.progressBar.visibility = View.GONE
                        }
                        is Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(context, resource.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                .collect()
        }


    }


    private fun setupRV() {
        val divider = DividerItemDecoration(context, RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider, null)?.let {
            divider.setDrawable(it)
        }
        adsAdapter = AdsAdapter()
        binding.recyclerView.apply {
            addItemDecoration(divider)
            layoutManager = LinearLayoutManager(activity)
            adapter = adsAdapter
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.search_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.action_search -> {
                val sv = menuItem.actionView as SearchView
                sv.setOnQueryTextListener(this)
            }

        }
        return true
    }

    override fun onQueryTextSubmit(text: String?): Boolean {
        viewModel.getAds(text)
        return true
    }

    override fun onQueryTextChange(text: String?): Boolean {
        return true
    }


}