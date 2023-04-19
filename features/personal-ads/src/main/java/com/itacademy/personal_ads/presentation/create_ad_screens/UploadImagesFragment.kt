package com.itacademy.personal_ads.presentation.create_ad_screens

import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.itacademy.personal_ads.R
import com.itacademy.personal_ads.databinding.FragmentUploadImagesBinding
import com.itacademy.personal_ads.presentation.PersonalAdsViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream

@AndroidEntryPoint
class UploadImagesFragment : Fragment(R.layout.fragment_upload_images) {

    private val REQUEST_CODE_PICK_IMAGES = 100
    private val binding by viewBinding<FragmentUploadImagesBinding>()
    private val viewModel by viewModels<PersonalAdsViewModel>()
    //private val viewModel: UploadImagesViewModel by viewModels()
    private val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetMultipleContents()) { uris ->
        uris?.let {
            val images = mutableListOf<File>()
            it.forEach { uri ->
                val inputStream = requireContext().contentResolver.openInputStream(uri)
                val file = File(requireContext().cacheDir, uri.lastPathSegment!!)
                inputStream.use { input ->
                    file.outputStream().use { output ->
                        input!!.copyTo(output)
                    }
                }
                images.add(file)
            }
            viewModel.uploadImages(5L, images)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.upload.setOnClickListener {
            galleryLauncher.launch("image/*")
        }
    }

  /*  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_PICK_IMAGES && resultCode == Activity.RESULT_OK) {
            val clipData = data?.clipData
            val uriList = mutableListOf<Uri>()

            if (clipData != null) {
                for (i in 0 until clipData.itemCount) {
                    val uri = clipData.getItemAt(i).uri
                    uriList.add(uri)
                }
            } else {
                val uri = data?.data
                if (uri != null) {
                    uriList.add(uri)
                }
            }

            val files = mutableListOf<File>()
            val contentResolver = requireContext().contentResolver

            uriList.forEach { uri ->
                val inputStream = contentResolver.openInputStream(uri)
                val fileName = getFileNameFromUri(uri, contentResolver)
                val file = File(requireContext().cacheDir, fileName)

                inputStream?.use { input ->
                    file.outputStream().use { output ->
                        input.copyTo(output)
                    }
                }

                files.add(file)
            }

            viewModel.uploadImages(adId = 5L, images = files.toList())
        }
    }

    private fun getFileNameFromUri(uri: Uri, contentResolver: ContentResolver): String {
        val cursor = contentResolver.query(uri, null, null, null, null)
        return cursor?.use {
            it.moveToFirst()
            val index = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            it.getString(index)
        } ?: "unknown"
    }*/

}