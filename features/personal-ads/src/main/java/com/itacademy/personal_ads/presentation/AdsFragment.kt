package com.itacademy.personal_ads.presentation

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.itacademy.common.Resource
import com.itacademy.navigation.NavCommand
import com.itacademy.navigation.NavCommands
import com.itacademy.navigation.navigate
import com.itacademy.personal_ads.R
import com.itacademy.personal_ads.databinding.FragmentAdsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AdsFragment : Fragment(R.layout.fragment_ads) {

    private lateinit var adsAdapter: MyAdsAdapter
    private val binding by viewBinding<FragmentAdsBinding>()
    private val viewModel by viewModels<PersonalAdsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRV()

        val swipe = binding.swipeAds

        swipe.setOnRefreshListener {
            viewModel.getAds()
            swipe.isRefreshing = false
        }

        with(binding) {
            btnEnter.setOnClickListener {
                navigate(
                    NavCommand(
                        NavCommands.DeepLink(
                            url = Uri.parse("waceplare://new")
                        )
                    )
                )
            }
        }


        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.ads
                .onEach { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            val data = resource.data
                            binding.btnSignIn.visibility = View.GONE
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
                            binding.btnSignIn.visibility = View.GONE
                        }

                        is Resource.Error -> {
                            with(binding) {
                                clMain.visibility = View.GONE
                                btnSignIn.visibility = View.VISIBLE

                                btnSignIn.setOnClickListener {
                                    navigate(
                                        NavCommand(
                                            NavCommands.DeepLink(
                                                url = Uri.parse("waceplare://login"),
                                                isSingleTop = true
                                            )
                                        )
                                    )
                                }
                            }


                        }
                    }
                }
                .collect()
        }


    }


    private fun setupRV() {
        val dividerItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)

        dividerItemDecoration.setDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                com.itacademy.theme.R.drawable.default_divider
            )!!
        )

        adsAdapter = MyAdsAdapter()
        binding.recyclerView.apply {
            addItemDecoration(dividerItemDecoration)
            layoutManager = LinearLayoutManager(activity)
            adapter = adsAdapter
        }

    }

    companion object {
        fun newInstance(): AdsFragment {
            return AdsFragment()
        }
    }
}