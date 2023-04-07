package com.itacademy.personal_ads.presentation.create_ad_screens.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itacademy.personal_ads.databinding.ItemCategoryBinding
import com.itacademy.personal_ads.domain.model.StateAd


interface OnStateAdClickListener {
    fun onStateAdClick(stateAd: StateAd)
}


class StateAdAdapter(private val clickListener: OnStateAdClickListener) :
    RecyclerView.Adapter<StateAdAdapter.StateAdVH>() {

    private val statesAd: Array<StateAd> = StateAd.values()


    inner class StateAdVH(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateAdAdapter.StateAdVH =
        StateAdVH(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int {
        return statesAd.size
    }


    override fun onBindViewHolder(holder: StateAdAdapter.StateAdVH, position: Int) {
        val statesAd = statesAd[position]
        with(holder.binding) {
            tvCategoryName.text = statesAd.getDisplayName()
            holder.itemView.setOnClickListener {
                clickListener?.onStateAdClick(statesAd)
            }
        }
    }


}