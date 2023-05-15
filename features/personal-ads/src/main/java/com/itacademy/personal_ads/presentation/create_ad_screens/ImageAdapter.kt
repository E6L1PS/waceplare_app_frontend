package com.itacademy.personal_ads.presentation.create_ad_screens

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.itacademy.personal_ads.databinding.ItemUploadPhotoBinding

class ImageAdapter(private val uris: List<Uri>) : RecyclerView.Adapter<ImageAdapter.ImageVH>() {

    inner class ImageVH(val binding: ItemUploadPhotoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageVH {
        return ImageVH(
            ItemUploadPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = uris.size

    override fun onBindViewHolder(holder: ImageVH, position: Int) {
        Glide.with(holder.itemView)
            .load(uris[position])
            .into(holder.binding.imageView)
    }
}