package com.example.carmarketsuftit.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.carmarketsuftit.R
import com.example.carmarketsuftit.databinding.FragmentAddCarBinding

class AddCarFragment : BaseFragment<FragmentAddCarBinding>() {

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAddCarBinding {
        return FragmentAddCarBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCancel.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_addCarFragment_to_carDetailsFragment)
        }


    }
}