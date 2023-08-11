package com.example.carmarketsuftit.utils

import android.content.Context
import android.graphics.BitmapFactory
import android.icu.util.Calendar
import androidx.core.graphics.createBitmap
import com.example.carmarketsuftit.R
import com.example.carmarketsuftit.model.Car

fun getStaticCar(context: Context): List<Car> {
    val startBmp = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.volga
    )
    val newBmp = createBitmap(250, 170, startBmp.config)

    return listOf(
        Car(
            0,
            "Volga",
            1992,
            5.5,
            newBmp,
            3800.0,
            Calendar.getInstance().getCurrentTime().toHumanReadableFormat()
        ),
        Car(
            0,
            "BMW",
            2020,
            3.5,
            newBmp,
            4800.0,
            Calendar.getInstance().getCurrentTime().toHumanReadableFormat()
        ),
        Car(
            0,
            "Audi A8",
            2010,
            4.5,
            newBmp,
            12000.0,
            Calendar.getInstance().getCurrentTime().toHumanReadableFormat()
        )
    )
}