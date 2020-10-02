package com.trapezoidlimited.groundforce.ui.onboarding

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
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

        /**move to next on boarding screen**/
        binding.btn.setOnClickListener {
            findNavController().navigate(R.id.signUpFragment)

            /***save on boarding state**/
            onBoardingFinish()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val sliderRunnable = Runnable {
        binding.viewPager.currentItem = binding.viewPager.currentItem + 1
    }

    /***set up recycler item**/
    private fun setUpOnBoardingItems() {
        val onBoardingItems = mutableListOf<OnBoardItem>()

        val itemLiveEarn = OnBoardItem(
             R.drawable.zaria_boy,
            R.string.splash_1_big_text,
           R.string.splash_1_small_text
        )

        val itemGetDone = OnBoardItem(
             R.drawable.abj_girl,
            R.string.splash_2_big_text,
            R.string.splash_2_small_text
        )

        val itemMoreWithGroundForce = OnBoardItem(
             R.drawable.lagos_girl,
            R.string.splash_3_big_text,
            R.string.splash_3_small_text
        )

        onBoardingItems.add(itemLiveEarn)
        onBoardingItems.add(itemGetDone)
        onBoardingItems.add(itemMoreWithGroundForce)

        onBoardingAdapter = OnBoardingAdapter(onBoardingItems)


    }

    /**check if on boarding screen have been visited before**/
    private fun onBoardingFinish(){
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)

        val editor = sharedPref.edit()

        editor.putBoolean("Finished", true)

        editor.apply()
    }


}