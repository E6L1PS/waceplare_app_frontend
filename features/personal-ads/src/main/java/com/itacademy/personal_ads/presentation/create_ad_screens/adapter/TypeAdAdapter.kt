package com.itacademy.personal_ads.presentation.create_ad_screens.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itacademy.personal_ads.R
import com.itacademy.personal_ads.databinding.ItemCategoryBinding
import com.itacademy.personal_ads.domain.model.TypeAd


interface OnTypeAdClickListener {
    fun onTypeAdClick(typeAd: TypeAd)
}


class TypeAdAdapter(private val clickListener: OnTypeAdClickListener) : RecyclerView.Adapter<TypeAdAdapter.TypeAdVH>() {

    private val typesAd: Array<TypeAd> = TypeAd.values()

    inner class TypeAdVH(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeAdAdapter.TypeAdVH =
        TypeAdVH(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int {
        return typesAd.size
    }


    override fun onBindViewHolder(holder: TypeAdAdapter.TypeAdVH, position: Int) {
        val typeAd = typesAd[position]
        with(holder.binding) {
            tvCategoryName.text = typeAd.getDisplayName()
            ivCategoryIcon.setImageResource(getIconResource(typeAd))
            holder.itemView.setOnClickListener {
                clickListener?.onTypeAdClick(typeAd)
            }
        }
    }

    private fun getIconResource(typeAd: TypeAd): Int {
        return when (typeAd) {
            TypeAd.CAR -> R.drawable.car_ic
            TypeAd.REAL_ESTATE -> R.drawable.real_estate_ic
            TypeAd.JOB -> R.drawable.job_ic
            TypeAd.SERVICE -> R.drawable.service_ic
            TypeAd.STUFF -> R.drawable.stuff_ic
            TypeAd.BUSINESS -> R.drawable.business_ic
        }
    }


}