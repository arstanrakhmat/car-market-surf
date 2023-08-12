package com.example.carmarketsuftit.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.carmarketsuftit.databinding.FragmentSubscriptionBottomSheetBinding
import com.example.carmarketsuftit.utils.CustomPreferences
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.android.ext.android.inject

class SubscriptionBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentSubscriptionBottomSheetBinding
    private val sharedPrefs by inject<CustomPreferences>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSubscriptionBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListeners()
    }

    private fun clickListeners() {
        binding.btnAdd.setOnClickListener {
            if (binding.etCarName.text.toString().length == 5) {
                sharedPrefs.saveSubscription("updated")
                Toast.makeText(requireContext(), "Вы успешно купили подписку", Toast.LENGTH_SHORT)
                    .show()
                dismiss()
            } else {
                Toast.makeText(requireContext(), "Должно быть 5 цивр", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }

}