package com.itacademy.favorites.presentation

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.itacademy.common.model.Ad
import com.itacademy.common.model.AdWithIsFavorite
import com.itacademy.favorites.R
import com.itacademy.favorites.databinding.ItemAdBinding

interface AdOnClickListener {
    fun adSelect(adId: Long)
    fun addFavorite(adId: Long)
    fun deleteFavorite(adId: Long)
}


class FavoritesAdapter(private val adOnClickListener: AdOnClickListener) : RecyclerView.Adapter<FavoritesAdapter.FavoritesVH>(), View.OnClickListener {

    inner class FavoritesVH(val binding: ItemAdBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Ad>() {
        override fun areItemsTheSame(oldItem: Ad, newItem: Ad): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Ad, newItem: Ad): Boolean {
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
        val ad = differ.currentList[position]

        with(holder.itemView) {
            tag = ad.id
            setOnClickListener(this@FavoritesAdapter)
        }

        with(holder.binding) {
            tvTitle.text = ad.title
            tvPrice.text = String.format("%,d", ad.price).replace(",", " ").plus(" Р")
            tvDateOfCreation.text = ad.dateOfCreated

            if (!ad.status) {
                val color = ContextCompat.getColor(holder.itemView.context, com.itacademy.theme.R.color.gray_main)
                clFavorite.setBackgroundColor(color)
                tvTitle.text = "Снято с публикации"
            }

            //cbFavorite.isChecked = ad.isFavorite

   /*         if (ad.isFavorite) {
                ivFavorite.setImageResource(com.itacademy.theme.R.drawable.favorite_true_ic)
            } else {
                ivFavorite.setImageResource(com.itacademy.theme.R.drawable.favorite_false_ic)
            }*/

            ivFavorite.setImageResource(com.itacademy.theme.R.drawable.favorite_true_ic)
            Glide.with(holder.itemView.context)
                .load("https://picsum.photos/id/${ad.id}/500/500")
                .into(ivAd)

        }
    }

    override fun onClick(view: View) {
        val adId = view.tag as Long

        when (view.id) {

            else -> {
                adOnClickListener.adSelect(adId)
            }
        }

    }


}