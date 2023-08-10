package com.example.carmarketsuftit.utils

import android.icu.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.toHumanReadableFormat(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    return sdf.format(this)
}
