package com.example.carmarketsuftit.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.carmarketsuftit.databinding.FragmentCarDetailsBinding

class CarDetailsFragment : BaseFragment<FragmentCarDetailsBinding>() {
    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCarDetailsBinding {
        return FragmentCarDetailsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.arrowBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}