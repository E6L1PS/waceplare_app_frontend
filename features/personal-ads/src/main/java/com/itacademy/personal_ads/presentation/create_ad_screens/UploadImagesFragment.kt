package com.itacademy.personal_ads.presentation.create_ad_screens

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
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

    private val pickImagesLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val selectedByteArrayImages = mutableListOf<ByteArray?>()
                val clipData = result.data?.clipData

                CoroutineScope(Dispatchers.IO).launch {

                    if (clipData != null) {
                        for (i in 0 until clipData.itemCount) {
                            val uri = clipData.getItemAt(i).uri
                            val bytes = readBytes(uri)
                            Log.d("bytes___", bytes.toString())
                            selectedByteArrayImages.add(bytes)
                        }
                    } else {
                        val uri = result.data?.data
                        if (uri != null) {
                            val bytes = readBytes(uri)
                            selectedByteArrayImages.add(bytes)
                        }
                    }

                }


                Log.d("bytes___", selectedByteArrayImages.toString())

                viewModel.uploadImages(2, selectedByteArrayImages)
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext.setOnClickListener {

            selectImagesFromGallery()

            /*  navigate(
                  NavCommand(
                      NavCommands.DeepLink(
                          url = Uri.parse("waceplare://ads_fragment")
                      )
                  )

              )
  */
        }
    }

    private fun selectImagesFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        pickImagesLauncher.launch(intent)
    }

    private suspend fun readBytes(uri: Uri): ByteArray? = withContext(Dispatchers.IO) {
        val inputStream = requireContext().contentResolver.openInputStream(uri)
        inputStream?.use {
            it.readBytes()
        }
    }
}