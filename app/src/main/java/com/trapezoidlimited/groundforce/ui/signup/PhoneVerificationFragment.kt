package com.trapezoidlimited.groundforce.ui.signup

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.SpannedString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.trapezoidlimited.groundforce.R
import com.trapezoidlimited.groundforce.databinding.FragmentPhoneVerificationBinding
import com.trapezoidlimited.groundforce.databinding.FragmentSignUpBinding


class PhoneVerificationFragment : Fragment() {

    private var _binding : FragmentPhoneVerificationBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPhoneVerificationBinding.inflate(inflater, container, false)

        /**convert string to spannable and make some parts clickable**/
        val fullText = "By clicking \"Continue\", you are accepting our Terms & Conditions as well as our Privacy Policy"
        val spannableString = SpannableString(fullText)

       val applyColorSpan = ForegroundColorSpan(resources.getColor(R.color.colorSpan))
        val applyColorSpan2 = ForegroundColorSpan(resources.getColor(R.color.colorSpan))
        spannableString.setSpan(applyColorSpan,46,65,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(applyColorSpan2,80,94,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.tAndCTv.setText(spannableString)

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}