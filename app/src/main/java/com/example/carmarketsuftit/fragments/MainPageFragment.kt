package com.example.carmarketsuftit.fragments

import android.graphics.BitmapFactory
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.createBitmap
import androidx.navigation.fragment.findNavController
import com.example.carmarketsuftit.R
import com.example.carmarketsuftit.adapter.CarsAdapter
import com.example.carmarketsuftit.databinding.FragmentMainPageBinding
import com.example.carmarketsuftit.model.Car
import com.example.carmarketsuftit.utils.toHumanReadableFormat
import com.example.carmarketsuftit.viewModel.CarViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Date

class MainPageFragment : BaseFragment<FragmentMainPageBinding>() {

    private val carViewModel by viewModel<CarViewModel>()
    private lateinit var carAdapter: CarsAdapter

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
        setupObservers()

        setUpSearch()
    }

    private fun clickListeners() {
        binding.btnAddCar.setOnClickListener {
            findNavController().navigate(R.id.action_mainPageFragment_to_addCarFragment)
//            val bottomSheetFragment = SubscriptionBottomSheetFragment()
//            bottomSheetFragment.isCancelable = false
//            bottomSheetFragment.show(parentFragmentManager, tag)
        }

        carAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("car", it)
            }

            findNavController().navigate(R.id.action_mainPageFragment_to_carDetailsFragment, bundle)
        }
    }

    private fun setupObservers() {
        carViewModel.isDbEmptyValue.observe(viewLifecycleOwner) {
            Log.d("isDbEmpty", it.toString())

            if (it == true) {
                addDefaultCars()
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

    private fun addDefaultCars() {

        val startBmp = BitmapFactory.decodeResource(context?.resources, R.drawable.volga)
        val newBmp = createBitmap(250, 170, startBmp.config)

        carViewModel.addCar(
            Car(
                0,
                "Volga",
                1992,
                5.5,
                newBmp,
                3800.0,
                getCurrentTime().toHumanReadableFormat()
            )
        )

//        val list = listOf<Car>(
//            Car(
//                100,
//                "Volga",
//                1992,
//                5.5,
//                BitmapFactory.decodeResource(context?.resources, R.drawable.volga),
//                3800.0,
//                getCurrentTime().toHumanReadableFormat()
//            )
//        )
//
//        carAdapter.differ.submitList(list)
    }

    private fun getCurrentTime(): Date {
        return Calendar.getInstance().time
    }

}