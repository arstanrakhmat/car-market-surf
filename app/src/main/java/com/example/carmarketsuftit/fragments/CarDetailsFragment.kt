package com.example.carmarketsuftit.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.carmarketsuftit.databinding.FragmentCarDetailsBinding
import com.example.carmarketsuftit.utils.CustomPreferences
import org.koin.android.ext.android.inject

class CarDetailsFragment : BaseFragment<FragmentCarDetailsBinding>() {

    private val args: CarDetailsFragmentArgs by navArgs()
    private val sharedPrefs by inject<CustomPreferences>(

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Log.d(
//            "prefs",
//            "counterDefault:${sharedPrefs.fetchVisitValue()} " +
//                    "increment:${sharedPrefs.saveVisitValue()}" +
//                    "checKCounter: ${sharedPrefs.fetchVisitValue()}" +
//                    " subs: ${sharedPrefs.fetchSubscription()}" +
//                    "bySubs: ${sharedPrefs.saveSubscription("updated")}" +
//                    "checkNewSubs: ${sharedPrefs.fetchSubscription()}"
//        )
        sharedPrefs.saveVisitValue()
    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCarDetailsBinding {
        return FragmentCarDetailsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()

        binding.arrowBack.setOnClickListener {
            findNavController().navigateUp()
        }

    }

    private fun setData() {
        binding.ivCarImage.setImageBitmap(args.car.image)
        binding.tvCarName.text = args.car.name
        binding.tvCapacity.text = args.car.engine_capacity.toString()
        binding.tvPrice.text = args.car.price.toString()
        binding.tvYear.text = args.car.creationYear.toString()
        binding.publicData.text = args.car.publicationDate
    }
}