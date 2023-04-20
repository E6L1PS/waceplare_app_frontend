package com.itacademy.favorites.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.itacademy.common.model.AdWithIsFavorite
import com.itacademy.favorites.databinding.ItemAdBinding

class FavoritesAdapter : RecyclerView.Adapter<FavoritesAdapter.FavoritesVH>() {

    inner class FavoritesVH(val binding: ItemAdBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<AdWithIsFavorite>() {
        override fun areItemsTheSame(oldItem: AdWithIsFavorite, newItem: AdWithIsFavorite): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AdWithIsFavorite, newItem: AdWithIsFavorite): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesVH {
        return FavoritesVH(
            ItemAdBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: FavoritesVH, position: Int) {

    }

}