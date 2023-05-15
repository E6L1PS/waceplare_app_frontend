package com.itacademy.personal_ads.presentation.create_ad_screens

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.itacademy.navigation.NavCommand
import com.itacademy.navigation.NavCommands
import com.itacademy.navigation.navigate
import com.itacademy.personal_ads.R
import com.itacademy.personal_ads.databinding.FragmentUploadImagesBinding
import com.itacademy.personal_ads.presentation.PersonalAdsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class UploadImagesFragment : Fragment(R.layout.fragment_upload_images) {

    private val binding by viewBinding<FragmentUploadImagesBinding>()
    private val viewModel by viewModels<PersonalAdsViewModel>()
    private var imageUris: MutableList<Uri> = mutableListOf()
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("imageUris", ArrayList(imageUris))
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            imageUris = savedInstanceState.getParcelableArrayList<Uri>("imageUris")?.toMutableList()
                ?: mutableListOf()
            // Обновляем адаптер и список картинок
            binding.rvPhotos.adapter = ImageAdapter(imageUris)
            binding.rvPhotos.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private val pickImagesLauncher =
        registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia()) { uris ->
            imageUris.addAll(uris)
            binding.rvPhotos.adapter = ImageAdapter(imageUris)
            binding.rvPhotos.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.rvPhotos.adapter?.notifyDataSetChanged()
        }

    private fun uploadImages(uris: List<Uri>) = CoroutineScope(Dispatchers.IO).launch {
        val selectedByteArrayImages = mutableListOf<ByteArray?>()
        for (uri in uris) {
            val bytes = readBytes(uri)
            selectedByteArrayImages.add(bytes)
        }

        val adId = arguments?.getString("adId")?.toLong() ?: 0L

        if (adId != 0L) {
            viewModel.uploadImages(adId, selectedByteArrayImages)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.upload.imageView.setOnClickListener {
            selectImagesFromGallery()
        }

        binding.btnNext.setOnClickListener {
            if (imageUris.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "Please upload photo", Toast.LENGTH_LONG).show()
            } else {
                uploadImages(imageUris)
                /* TODO to add navigate
                navigate(
                    NavCommand(
                        NavCommands.DeepLink(
                            url = Uri.parse("waceplare://ads_fragment")
                        )
                    )

                )*/
            }
        }
    }

    private fun selectImagesFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        pickImagesLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private suspend fun readBytes(uri: Uri): ByteArray? = withContext(Dispatchers.IO) {
        val inputStream = requireContext().contentResolver.openInputStream(uri)
        inputStream?.use {
            it.readBytes()
        }
    }
}