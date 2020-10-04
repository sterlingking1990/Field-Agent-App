package com.trapezoidlimited.groundforce.ui.signup


import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.trapezoidlimited.groundforce.R
import com.trapezoidlimited.groundforce.databinding.FragmentPhoneActivationBinding


class PhoneActivationFragment : Fragment() {
    private var _binding : FragmentPhoneActivationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPhoneActivationBinding.inflate(inflater, container, false)




        /**show soft key to enable otp input**/
        showKeyboard(requireContext())
        binding.pinView.requestFocus()

        /**convert string to spannable and make some parts clickable**/
        val fullText = "Didn't get the code Resend."
        val spannableString = SpannableString(fullText)

        val clickableSpan1: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(requireContext(), "clicked", Toast.LENGTH_SHORT).show()
                TODO("add action to resend link")
            }
            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.BLUE
                ds.isUnderlineText = false
            }
        }
            /**set textView text to spannable string**/
        spannableString.setSpan(clickableSpan1, 20, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.resendTv.setText(spannableString)
        binding.resendTv.movementMethod = LinkMovementMethod.getInstance()

        /***move back to verification screen**/
        binding.activationBackArrow.setOnClickListener {
            findNavController().navigate(R.id.phoneVerificationFragment)
        }

       return  binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.pinView.setOtpCompletionListener {
            Log.d("onOtpCompleted=>", it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**showSoftKey function**/
    fun showKeyboard(context: Context) {
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInput(
            InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY
        )
    }

}