package com.itacademy.search.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.itacademy.search.R
import com.itacademy.search.databinding.ItemAdBinding
import com.itacademy.search.domain.model.Ad

class AdsAdapter : RecyclerView.Adapter<AdsAdapter.AdsVH>() {

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
        with(holder.binding) {
            tvTitle.text = ad.title
            tvPrice.text = String.format("%,d", ad.price).replace(",", " ").plus(" Р")
            tvDateOfCreation.text = ad.dateOfCreated
            ivAd.setImageResource(com.itacademy.theme.R.drawable.waceplare)
        }

    }

}