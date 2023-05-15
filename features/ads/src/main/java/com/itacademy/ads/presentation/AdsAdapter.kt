package com.itacademy.ads.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.itacademy.ads.databinding.ItemAdBinding
import com.itacademy.common.model.Ad

interface AdOnClickListener {
    fun adSelect(adId: Long)
    fun addFavorite(adId: Long)
    fun deleteFavorite(adId: Long)
}

class AdsAdapter(private val adOnClickListener: AdOnClickListener) :
    RecyclerView.Adapter<AdsAdapter.AdsVH>(), View.OnClickListener {

    inner class AdsVH(val binding: ItemAdBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Ad>() {

        override fun areItemsTheSame(oldItem: Ad, newItem: Ad): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Ad, newItem: Ad): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)
    val favoriteIds = mutableListOf<Long>()

    fun submitFavoriteIds(favoriteIds: List<Long>?) {
        this.favoriteIds.clear()
        if (favoriteIds != null) {
            this.favoriteIds.addAll(favoriteIds)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdsVH {
        return AdsVH(
            ItemAdBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: AdsVH, position: Int) {
        val ad = differ.currentList[position]

        with(holder.itemView) {
            tag = ad.id
            setOnClickListener(this@AdsAdapter)
        }

        with(holder.binding) {
            tvTitle.text = ad.title
            tvPrice.text = String.format("%,d", ad.price).replace(",", " ").plus(" Р")
            tvDateOfCreation.text = ad.dateOfCreated

            if (favoriteIds.contains(ad.id)) {
                ivFavorite.setImageResource(com.itacademy.theme.R.drawable.favorite_true_ic)
            } else {
                ivFavorite.setImageResource(com.itacademy.theme.R.drawable.favorite_false_ic)
            }

            ivFavorite.setOnClickListener {
                if (favoriteIds.contains(ad.id)) {
                    adOnClickListener.deleteFavorite(ad.id)
                    Log.d("GGGGdddd", "true")
                } else {
                    adOnClickListener.addFavorite(ad.id)
                    Log.d("GGGGdddd", "false")
                }
            }

            /*cbFavorite.setOnCheckedChangeListener { _, isChecked ->

                if (isChecked) {
                    Log.d("CHECKED_ADD", "add  ${ad.title}")
                    adOnClickListener.addFavorite(ad.id)
                } else {
                    Log.d("CHECKED_ADD", "delete ${ad.title}")
                    adOnClickListener.deleteFavorite(ad.id)
                }

            }*/

            Glide.with(holder.itemView.context)
                .load("http://192.168.0.106:8080/api/v1/ads/${ad.id}/image")
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