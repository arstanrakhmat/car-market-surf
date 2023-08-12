package com.example.carmarketsuftit.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.icu.util.Calendar
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.drawToBitmap
import androidx.navigation.fragment.findNavController
import com.example.carmarketsuftit.databinding.FragmentAddCarBinding
import com.example.carmarketsuftit.model.Car
import com.example.carmarketsuftit.utils.CustomPreferences
import com.example.carmarketsuftit.utils.toHumanReadableFormat
import com.example.carmarketsuftit.viewModel.CarViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Date

class AddCarFragment : BaseFragment<FragmentAddCarBinding>() {
    companion object {
        const val IMAGE_REQUEST = 100
    }

    private val carViewModel by viewModel<CarViewModel>()
    private val sharedPrefs by inject<CustomPreferences>()

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAddCarBinding {
        return FragmentAddCarBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickListeners()
        setupObserver()
    }

    private fun clickListeners() {
        binding.addPhotoButton.setOnClickListener {
            pickImageGallery()
        }

        binding.btnCancel.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnAdd.setOnClickListener {
//            findNavController().navigate(R.id.action_addCarFragment_to_carDetailsFragment)
            insertToDb()
        }
    }

    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST)
    }

    private fun insertToDb() {
        val image = binding.ivCarImage
        val name = binding.etCarName.text
        val price = binding.etCarPrice.text
        val capacity = binding.etCarEngieCapacity.text
        val creationYear = binding.etCarYear.text
        val publicationDate = getCurrentTime().toHumanReadableFormat()

        if (!inputCheck(
                image, name, price, capacity, creationYear
            )
        ) {
            val car = Car(
                0,
                name.toString(),
                creationYear.toString().toLong(),
                capacity.toString().toDouble(),
                image.drawToBitmap(),
                price.toString().toDouble(),
                publicationDate
            )
            Toast.makeText(requireContext(), "Filled", Toast.LENGTH_SHORT).show()
            Log.d("data", car.toString())

            carViewModel.addCar(car)

        } else {
            Toast.makeText(requireContext(), "-----", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupObserver() {
        carViewModel.isDataSaved.observe(viewLifecycleOwner) {
            it?.let {
                if (it) {
                    Toast.makeText(requireContext(), "Машина добавлена", Toast.LENGTH_SHORT).show()
                    sharedPrefs.saveAddedCarCounter()
                    findNavController().navigateUp()
                }
            }
        }
    }

    private fun getCurrentTime(): Date {
        return Calendar.getInstance().time
    }

    private fun inputCheck(
        image: ImageView,
        name: Editable,
        price: Editable,
        engineCapacity: Editable,
        creationYear: Editable
    ): Boolean {
        return (image.drawable == null ||
                TextUtils.isEmpty(name) ||
                TextUtils.isEmpty(price) ||
                TextUtils.isEmpty(engineCapacity) ||
                TextUtils.isEmpty(creationYear))

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK) {
            val path: Uri? = data?.data
            binding.ivCarImage.setImageURI(path)
        }
    }
}