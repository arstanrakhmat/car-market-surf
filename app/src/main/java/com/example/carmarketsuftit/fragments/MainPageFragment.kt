package com.example.carmarketsuftit.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.carmarketsuftit.R
import com.example.carmarketsuftit.databinding.FragmentMainPageBinding

class MainPageFragment : BaseFragment<FragmentMainPageBinding>() {

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainPageBinding {
        return FragmentMainPageBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListeners()
    }

    private fun clickListeners() {
        binding.btnAddCar.setOnClickListener {
//            findNavController().navigate(R.id.action_mainPageFragment_to_addCarFragment)
            val bottomSheetFragment = SubscriptionBottomSheetFragment()
            bottomSheetFragment.isCancelable = false
            bottomSheetFragment.show(parentFragmentManager, tag)
        }
    }
}