package com.trapezoidlimited.groundforce.ui.onboarding

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.trapezoidlimited.groundforce.R
import com.trapezoidlimited.groundforce.databinding.FragmentIntroBinding


class IntroFragment : Fragment() {
    private var _binding : FragmentIntroBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /**move to view pager fragment after 3secs**/
        Handler(Looper.myLooper()!!).postDelayed({
            /***if the user has already navigated through the boarding screen before, send the user to the sign up fragment**/
            if(onBoardingFinish()){
                findNavController().navigate(R.id.signUpFragment)
            }
            else{
                findNavController().navigate(R.id.onBoardingFragment)
            }

        }, 2000)

        /**Inflate the layout for this fragment using viewBinding**/
        _binding = FragmentIntroBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        /***set binding back to null**/
        _binding = null
    }

    /**check on boarding state**/
    private fun onBoardingFinish(): Boolean{
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)

        return sharedPref.getBoolean("Finished", false)
    }
}