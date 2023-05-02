package com.itacademy.search.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.itacademy.search.databinding.ItemImageBinding

class ImagesAdapter(val list: List<Long>) : RecyclerView.Adapter<ImagesAdapter.ImagesVH>() {

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

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ImagesVH, position: Int) {
        holder.binding.tvImageCounter.text = "${position + 1} - $itemCount"
        Glide.with(holder.itemView.context)
            .load("https://picsum.photos/id/${list[position]}/500/500")
            .into(holder.binding.ivImage)
    }

}