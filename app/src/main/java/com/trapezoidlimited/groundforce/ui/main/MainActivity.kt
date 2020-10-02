package com.trapezoidlimited.groundforce.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.trapezoidlimited.groundforce.R
import com.trapezoidlimited.groundforce.model.OnBoardItem
import com.trapezoidlimited.groundforce.ui.adapter.OnBoardingAdapter
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.lang.Runnable
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var onBoardingAdapter: OnBoardingAdapter
    private val sliderHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpOnBoardingItems()
        main_onBoarding_vp.adapter = onBoardingAdapter

        setUpOnBoardingIndicators()
        setCurrentOnBoardingIndicator(0)

        main_onBoarding_vp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentOnBoardingIndicator(position)

                sliderHandler.removeCallbacks(sliderRunnable)
                sliderHandler.postDelayed(sliderRunnable, 3000)



//                GlobalScope.launch(Dispatchers.Default) {
//                    delay(3000)
//                    withContext(Dispatchers.Main) {
//                        main_onBoarding_vp.currentItem += 1
//                    }
//                }

            }
        })
    }

    private val sliderRunnable = Runnable {
        main_onBoarding_vp.currentItem = main_onBoarding_vp.currentItem + 1
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

    private fun setUpOnBoardingIndicators() {
        val indicators = arrayOfNulls<ImageView>(onBoardingAdapter.itemCount)

        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )

        layoutParams.setMargins(8, 0, 8, 0)

        for (i in indicators.indices) {
            indicators[i] = ImageView(this)

            indicators[i]?.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.onboarding_indicator_inactive
                )
            )

            indicators[i]?.layoutParams = layoutParams
            layoutOnBoardingIndicators.addView(indicators[i])
        }
    }

    private fun setCurrentOnBoardingIndicator(index: Int) {
        val childCount = layoutOnBoardingIndicators.childCount

        for (i in 0 until childCount) {
            val imageView: ImageView = layoutOnBoardingIndicators.getChildAt(i) as ImageView

            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.onboarding_indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.onboarding_indicator_inactive
                    )
                )
            }
        }
    }


}

