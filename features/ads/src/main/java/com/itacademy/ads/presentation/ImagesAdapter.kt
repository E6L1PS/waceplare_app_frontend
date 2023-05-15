package com.itacademy.ads.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.itacademy.ads.databinding.ItemImageBinding
import com.itacademy.common.model.AdImage

class ImagesAdapter(val images: List<AdImage>) : RecyclerView.Adapter<ImagesAdapter.ImagesVH>() {

    inner class ImagesVH(val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesVH {
        return ImagesVH(
            ItemImageBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: ImagesVH, position: Int) {
        holder.binding.tvImageCounter.text = "${position + 1} - $itemCount"
        Glide.with(holder.itemView.context)
            .load("http://192.168.0.106:8080/api/v1/ads/image?url=${images[position].url}")
            .into(holder.binding.ivImage)
    }

}