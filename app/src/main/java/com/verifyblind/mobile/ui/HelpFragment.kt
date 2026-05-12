package com.verifyblind.mobile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.verifyblind.mobile.BuildConfig
import com.verifyblind.mobile.databinding.FragmentHelpBinding

class HelpFragment : Fragment() {

    private var _binding: FragmentHelpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHelpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener { findNavController().navigateUp() }

        binding.tvVersion.text = "VERSION ${BuildConfig.VERSION_NAME.uppercase()}-STABLE"

        setupFaqItem(binding.faqItem1, binding.faqAnswer1, binding.faqArrow1)
        setupFaqItem(binding.faqItem2, binding.faqAnswer2, binding.faqArrow2)
        setupFaqItem(binding.faqItem3, binding.faqAnswer3, binding.faqArrow3)
        setupFaqItem(binding.faqItem4, binding.faqAnswer4, binding.faqArrow4)
        setupFaqItem(binding.faqItem5, binding.faqAnswer5, binding.faqArrow5)
        setupFaqItem(binding.faqItem6, binding.faqAnswer6, binding.faqArrow6)
        setupFaqItem(binding.faqItem7, binding.faqAnswer7, binding.faqArrow7)
        setupFaqItem(binding.faqItem8, binding.faqAnswer8, binding.faqArrow8)
        setupFaqItem(binding.faqItem9, binding.faqAnswer9, binding.faqArrow9)
        setupFaqItem(binding.faqItem10, binding.faqAnswer10, binding.faqArrow10)
        setupFaqItem(binding.faqItem11, binding.faqAnswer11, binding.faqArrow11)
        setupFaqItem(binding.faqItem12, binding.faqAnswer12, binding.faqArrow12)
        setupFaqItem(binding.faqItem13, binding.faqAnswer13, binding.faqArrow13)
        setupFaqItem(binding.faqItem14, binding.faqAnswer14, binding.faqArrow14)
        setupFaqItem(binding.faqItem15, binding.faqAnswer15, binding.faqArrow15)
        setupFaqItem(binding.faqItem16, binding.faqAnswer16, binding.faqArrow16)
/*
        binding.btnContact.setOnClickListener {
            // İleride destek e-postası veya URL açılabilir
        }
*/
    }

    private fun setupFaqItem(container: View, answer: TextView, arrow: ImageView) {
        container.setOnClickListener {
            val isOpen = answer.visibility == View.VISIBLE
            answer.visibility = if (isOpen) View.GONE else View.VISIBLE
            arrow.rotation = if (isOpen) 90f else 270f
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
