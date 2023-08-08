package com.example.carmarketsuftit.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.carmarketsuftit.databinding.FragmentSettingsBinding

class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSettingsBinding {
        return FragmentSettingsBinding.inflate(inflater, container, false)
    }

}