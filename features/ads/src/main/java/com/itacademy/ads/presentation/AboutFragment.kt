package com.itacademy.ads.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import by.kirich1409.viewbindingdelegate.viewBinding
import com.itacademy.ads.R
import com.itacademy.ads.databinding.FragmentAboutBinding
import com.itacademy.common.Resource
import com.itacademy.common.model.AdImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class AboutFragment : Fragment(R.layout.fragment_about) {

    private val binding by viewBinding<FragmentAboutBinding>()
    private val viewModel by viewModels<AboutViewModel>()
    private lateinit var imagesAdapter: ImagesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.ad.collect { resource ->
                with(binding) {
                    when (resource) {
                        is Resource.Success -> {
                            //TODO write it right
                            val ad = resource.data
                            tvPrice.text = "${ad!!.price} Р"
                            tvTitle.text = ad!!.title
                            tvUser.text = "${ad.userInfo.firstname} ${ad.userInfo.lastname}"
                            tvRating.text = "${ad.userInfo.rating} ☆☆☆☆☆"
                            tvCharacteristic.text = getString(R.string.ad_characteristic_info, ad.type.getDisplayName(),
                                ad.state?.getDisplayName() ?: "отсутствует"
                            )
                            tvDescription.text = ad!!.description
                            tvAdditionalInfo.text = getString(R.string.ad_info, ad.id, ad.dateOfCreated, ad.views)
                            Toast.makeText(context, "Success", Toast.LENGTH_LONG).show()
                            setupRV(ad.images)

                            binding.btnCall.setOnClickListener {
                                val intent = Intent(Intent.ACTION_DIAL)
                                intent.data = Uri.parse("tel:${ad.userInfo.number}")
                                startActivity(intent)
                            }

                        }

                        is Resource.Loading -> {
                            Toast.makeText(context, "Loading", Toast.LENGTH_LONG).show()
                        }

                        is Resource.Error -> {
                            Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                        }

                    }
                }
            }
        }


    }


    private fun setupRV(images: List<AdImage>) {
        imagesAdapter = ImagesAdapter(images)

        binding.rvImages.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = imagesAdapter
            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }

    }


}
