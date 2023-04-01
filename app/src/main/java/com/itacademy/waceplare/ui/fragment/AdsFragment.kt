package com.itacademy.waceplare.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.itacademy.waceplare.R
import com.itacademy.waceplare.data.model.Category
import com.itacademy.waceplare.databinding.FragmentAdsBinding
import com.itacademy.waceplare.domain.model.AdDTO
import com.itacademy.waceplare.ui.adapter.AdsAdapter
import com.itacademy.waceplare.ui.adapter.MyAdsAdapter
import com.itacademy.waceplare.ui.view.MyAdsViewModel
import com.itacademy.waceplare.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AdsFragment : Fragment(R.layout.fragment_ads) {

    private lateinit var adsAdapter: MyAdsAdapter
    private val binding by viewBinding<FragmentAdsBinding>()
    private val viewModel by viewModels<MyAdsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRV()

        lifecycleScope.launchWhenStarted {
            with(binding) {
                btnEnter.setOnClickListener {
                    // TODO create a new fragment for post ad
                    viewModel.postAd(
                        ad = AdDTO(
                            120,
                            "title",
                            "desc",
                            category = Category(1, "Transport")
                        )
                    )
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.ads
                .onEach { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            val data = resource.data

                            if (data.isNullOrEmpty()) {
                                binding.tvEmptyList2.visibility = View.VISIBLE
                            } else {
                                binding.tvEmptyList2.visibility = View.GONE
                            }

                            adsAdapter.differ.submitList(data)
                            binding.progressBar2.visibility = View.GONE
                        }
                        is Resource.Loading -> {
                            binding.progressBar2.visibility = View.VISIBLE
                        }
                        is Resource.Error -> {
                            binding.progressBar2.visibility = View.GONE
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
        adsAdapter = MyAdsAdapter()
        binding.recyclerView.apply {
            addItemDecoration(divider)
            layoutManager = LinearLayoutManager(activity)
            adapter = adsAdapter
        }
    }
}