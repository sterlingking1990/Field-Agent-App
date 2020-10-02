package com.trapezoidlimited.groundforce.ui.onboarding

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.trapezoidlimited.groundforce.R
import com.trapezoidlimited.groundforce.databinding.FragmentOnBoardingBinding
import com.trapezoidlimited.groundforce.ui.onboarding.model.OnBoardItem
import com.trapezoidlimited.groundforce.ui.onboarding.adapter.OnBoardingAdapter


class OnBoardingFragment : Fragment() {

    private var _binding : FragmentOnBoardingBinding? = null
    private val binding get() = _binding!!

    private lateinit var onBoardingAdapter: OnBoardingAdapter
    private val sliderHandler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /**Inflate the layout for this fragment using viewBinding**/
        _binding = FragmentOnBoardingBinding.inflate(inflater, container, false)


        setUpOnBoardingItems()
        binding.viewPager.adapter = onBoardingAdapter

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                //setCurrentOnBoardingIndicator(position)

                sliderHandler.removeCallbacks(sliderRunnable)
                sliderHandler.postDelayed(sliderRunnable, 3000)

            }
        })


        /**setting the tab layout using tab layout mediator**/
        TabLayoutMediator(binding.tab, binding.viewPager){ t, position ->

        }.attach() /**connect tab to viewpager**/


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val sliderRunnable = Runnable {
        binding.viewPager.currentItem = binding.viewPager.currentItem + 1
    }

    private fun setUpOnBoardingItems() {
        val onBoardingItems = mutableListOf<OnBoardItem>()

        val itemLiveEarn = OnBoardItem(
            onBoardingImage = R.drawable.zaria_boy,
            onBoardingHeaderText = "Earn Because You \nLive Where You Live",
            onBoardingSubText = "Complete simple tasks on your mobile \nphone and around your location."
        )

        val itemGetDone = OnBoardItem(
            onBoardingImage = R.drawable.abj_girl,
            onBoardingHeaderText = "Don't Get too far to \nGet Things Done",
            onBoardingSubText = "Distance is not a barrier. Verify addresses \nclose to you and earn cool cash!"
        )

        val itemMoreWithGroundForce = OnBoardItem(
            onBoardingImage = R.drawable.lagos_girl,
            onBoardingHeaderText = "Do more, Earn more \nWith GroundForce",
            onBoardingSubText = "More missions, more survey, more \nmoney!"
        )

        onBoardingItems.add(itemLiveEarn)
        onBoardingItems.add(itemGetDone)
        onBoardingItems.add(itemMoreWithGroundForce)

        onBoardingAdapter = OnBoardingAdapter(onBoardingItems)


    }


}