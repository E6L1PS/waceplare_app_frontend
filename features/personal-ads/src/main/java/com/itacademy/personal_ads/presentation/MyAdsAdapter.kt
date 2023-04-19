package com.itacademy.personal_ads.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.itacademy.common.model.Ad
import com.itacademy.personal_ads.databinding.ItemMyAdBinding

class MyAdsAdapter : RecyclerView.Adapter<MyAdsAdapter.MyAdsVH>() {

    inner class MyAdsVH(val binding: ItemMyAdBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Ad>() {
        override fun areItemsTheSame(oldItem: Ad, newItem: Ad): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Ad, newItem: Ad): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdsVH {
        return MyAdsVH(
            ItemMyAdBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: MyAdsVH, position: Int) {
        val ad = differ.currentList[position]
        with(holder.binding) {
            tvMyTitle.text = ad.title
            tvMyPrice.text = String.format("%,d", ad.price).replace(",", " ").plus(" Р")
            Glide.with(holder.itemView.context)
                .load("http://192.168.0.106:8080/api/v1/ads/${ad.id}/image")
                .into(ivMyAd)
            tvViews.text = ad.views.toString()

        }
    }

}