package com.trapezoidlimited.groundforce.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.trapezoidlimited.groundforce.R
import com.trapezoidlimited.groundforce.model.OnBoardItem
import kotlinx.android.synthetic.main.item_container_onboarding.view.*

class OnBoardingAdapter(var onBoardItems: MutableList<OnBoardItem>): RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    inner class OnBoardingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val imageViewHeader: ImageView = itemView.main_item_onBoarding_iv
        private val textViewHeader: TextView = itemView.main_item_onBoarding_header_tv
        private val textViewSubText: TextView = itemView.main_item_onBoarding_subtext_tv

        fun setOnBoardingData(onBoardItem: OnBoardItem) {
            imageViewHeader.setImageResource(onBoardItem.onBoardingImage)
            textViewHeader.text = onBoardItem.onBoardingHeaderText
            textViewSubText.text = onBoardItem.onBoardingSubText
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_container_onboarding, parent, false)

        return OnBoardingViewHolder(view)
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.setOnBoardingData(onBoardItems[position])

    }

    override fun getItemCount(): Int {
        return onBoardItems.size
    }


}