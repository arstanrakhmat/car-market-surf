package com.example.carmarketsuftit.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.carmarketsuftit.R
import com.example.carmarketsuftit.databinding.FragmentSettingsBinding
import com.example.carmarketsuftit.utils.CustomPreferences
import org.koin.android.ext.android.inject

class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {

    private val sharedPrefs by inject<CustomPreferences>()

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSettingsBinding {
        return FragmentSettingsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListeners()
    }

    private fun clickListeners() {
        binding.btnReset.setOnClickListener {
            alertDialog()
        }
    }

    private fun alertDialog() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(R.string.reset_title)

        builder.setPositiveButton(R.string.yes) { dialog, _ ->
            sharedPrefs.resetSubscription()
            sharedPrefs.resetAddedCounter()
            sharedPrefs.resetVisitValue()
            Toast.makeText(context, "Подписка обнулена", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        builder.setNegativeButton(R.string.no) { dialog, _ ->
            dialog.dismiss()
        }

        builder.show()
    }

}