package com.example.carmarketsuftit.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.carmarketsuftit.R
import com.example.carmarketsuftit.adapter.CarsAdapter
import com.example.carmarketsuftit.databinding.FragmentMainPageBinding
import com.example.carmarketsuftit.utils.CustomPreferences
import com.example.carmarketsuftit.viewModel.CarViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainPageFragment : BaseFragment<FragmentMainPageBinding>() {

    private val carViewModel by viewModel<CarViewModel>()
    private lateinit var carAdapter: CarsAdapter
    private val sharedPrefs by inject<CustomPreferences>()

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainPageBinding {
        return FragmentMainPageBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        clickListeners()
//        carViewModel.isDbEmpty()
        carViewModel.getAllData().observe(viewLifecycleOwner) {
            carAdapter.differ.submitList(it)
        }

        setUpSearch()
    }

    private fun clickListeners() {
        binding.btnAddCar.setOnClickListener {
            if (sharedPrefs.fetchSubscription() == "sms" && sharedPrefs.fetchAddedCarCounter() > 1) {
                val bottomSheetFragment = SubscriptionBottomSheetFragment()
                bottomSheetFragment.isCancelable = false
                bottomSheetFragment.show(parentFragmentManager, tag)
            } else {
                findNavController().navigate(R.id.action_mainPageFragment_to_addCarFragment)
            }
//            val bottomSheetFragment = SubscriptionBottomSheetFragment()
//            bottomSheetFragment.isCancelable = false
//            bottomSheetFragment.show(parentFragmentManager, tag)
        }

        carAdapter.setOnItemClickListener {
            if (sharedPrefs.fetchSubscription() == "sms" && sharedPrefs.fetchVisitValue() > 2 && sharedPrefs.fetchSubscription() == "sms") {
                val bottomSheetFragment = SubscriptionBottomSheetFragment()
                bottomSheetFragment.isCancelable = false
                bottomSheetFragment.show(parentFragmentManager, tag)
            } else {
                val bundle = Bundle().apply {
                    putSerializable("car", it)
                }

                findNavController().navigate(
                    R.id.action_mainPageFragment_to_carDetailsFragment,
                    bundle
                )
            }
        }
    }

    private fun setUpSearch() {
        binding.searchView.apply {
            isSubmitButtonEnabled = true
            setOnQueryTextListener(object :
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    if (p0 != null) {
                        searchCar(querySearch = p0)
                    }
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    if (p0 != null) {
                        searchCar(p0)
                    }
                    return true
                }
            })
        }
    }

    private fun searchCar(querySearch: String) {
        val searchQuery = "%$querySearch%"

        carViewModel.getSearchCar(searchQuery).observe(viewLifecycleOwner) {
            it.let {
                carAdapter.differ.submitList(it)
            }
        }
    }

    private fun setupAdapter() {
        carAdapter = CarsAdapter()
        binding.rvCars.apply {
            adapter = carAdapter
        }
    }
}