package com.itacademy.search.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.itacademy.common.model.AdWithIsFavorite
import com.itacademy.search.databinding.ItemAdBinding

interface AdOnClickListener {
    fun adSelect(adId: Long)
    fun addFavorite(adId: Long)
    fun deleteFavorite(adId: Long)
}

class AdsAdapter(private val adOnClickListener: AdOnClickListener) :
    RecyclerView.Adapter<AdsAdapter.AdsVH>(), View.OnClickListener {

    inner class AdsVH(val binding: ItemAdBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<AdWithIsFavorite>() {
        override fun areItemsTheSame(oldItem: AdWithIsFavorite, newItem: AdWithIsFavorite): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AdWithIsFavorite, newItem: AdWithIsFavorite): Boolean {
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

        with(holder.itemView) {
            tag = ad.id
            setOnClickListener(this@AdsAdapter)
        }

        with(holder.binding) {
            tvTitle.text = ad.title
            tvPrice.text = String.format("%,d", ad.price).replace(",", " ").plus(" Р")
            tvDateOfCreation.text = ad.dateOfCreated


            //cbFavorite.isChecked = ad.isFavorite

            if (ad.isFavorite) {
                ivFavorite.setImageResource(com.itacademy.theme.R.drawable.favorite_true_ic)
            } else {
                ivFavorite.setImageResource(com.itacademy.theme.R.drawable.favorite_false_ic)
            }

            ivFavorite.setOnClickListener {
                if (ad.isFavorite) {
                    adOnClickListener.deleteFavorite(ad.id)
                    Log.d("GGGGdddd", "true")
                } else {
                    adOnClickListener.addFavorite(ad.id)
                    Log.d("GGGGdddd", "false")
                }
            }

            Log.d("CHECKED_ADD", "isFavorite  ${ad.id} ${ad.isFavorite}")

            /*cbFavorite.setOnCheckedChangeListener { _, isChecked ->

                if (isChecked) {
                    Log.d("CHECKED_ADD", "add  ${ad.title}")
                    adOnClickListener.addFavorite(ad.id)
                } else {
                    Log.d("CHECKED_ADD", "delete ${ad.title}")
                    adOnClickListener.deleteFavorite(ad.id)
                }

            }*/

            //cbFavorite.isChecked = ad.isFavorite
            Log.d("CHECKED_ADD",  ad.isFavorite.toString())
            //val random = Random.nextInt(500)

            //http://192.168.0.106:8080/api/v1/ads/${ad.id}/image
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