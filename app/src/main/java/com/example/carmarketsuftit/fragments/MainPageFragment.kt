package com.example.carmarketsuftit.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.carmarketsuftit.databinding.FragmentMainPageBinding

class MainPageFragment : BaseFragment<FragmentMainPageBinding>() {

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainPageBinding {
        return FragmentMainPageBinding.inflate(inflater, container, false)
    }
}