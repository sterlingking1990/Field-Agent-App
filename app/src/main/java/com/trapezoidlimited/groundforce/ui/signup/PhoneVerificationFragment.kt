package com.trapezoidlimited.groundforce.ui.signup

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.navigateUp
import com.trapezoidlimited.groundforce.R
import com.trapezoidlimited.groundforce.databinding.FragmentPhoneVerificationBinding


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

        val clickableSpan1: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(requireContext(), "clicked", Toast.LENGTH_SHORT).show()
                TODO("add action to continue string")
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.BLUE
                ds.isUnderlineText = false
            }
        }

        val clickableSpan2: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(requireContext(), "clicked", Toast.LENGTH_SHORT).show()
                TODO("add action to Privacy Policy string")
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.BLUE
                ds.isUnderlineText = false
            }
        }

        spannableString.setSpan(clickableSpan1, 40, 65, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(clickableSpan2, 80, 94, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.tAndCTv.setText(spannableString)
        binding.tAndCTv.setMovementMethod(LinkMovementMethod.getInstance())

        /***move to phone activation screen**/
        binding.continueSignUpBtn.setOnClickListener {
            findNavController().navigate(R.id.phoneActivationFragment)
        }

        /**move back to welcome screen**/
        binding.verificationBackArrow.setOnClickListener {
            findNavController().navigate(R.id.signUpFragment)
        }
        return binding.root
    }




    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}