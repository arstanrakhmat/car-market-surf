package com.example.carmarketsuftit.utils

import android.content.Context
import android.content.SharedPreferences

class CustomPreferences(context: Context) {
    private val subscription: SharedPreferences =
        context.getSharedPreferences("App", Context.MODE_PRIVATE)
    private val subsEditor = subscription.edit()

    private val countVisit: SharedPreferences =
        context.getSharedPreferences("App2", Context.MODE_PRIVATE)
    private val visitEdit = countVisit.edit()

    private val countHowManyCarAdded: SharedPreferences =
        context.getSharedPreferences("App3", Context.MODE_PRIVATE)
    private val addedEdit = countHowManyCarAdded.edit()

    fun saveSubscription(value: String) {
        subsEditor.putString(Constants.SUBSCRIPTION, value).apply()
    }

    fun fetchSubscription(): String? {
        return subscription.getString(Constants.SUBSCRIPTION, "sms")
    }

    fun resetSubscription() {
        subsEditor.putString(Constants.SUBSCRIPTION, "sms").apply()
    }

    fun saveVisitValue() {
        val previousValue = fetchVisitValue()
        val newValue = previousValue + 1
        visitEdit.putLong(Constants.VISIT_COUNTER, newValue).apply()
    }

    fun fetchVisitValue(): Long {
        return countVisit.getLong(Constants.VISIT_COUNTER, 0)
    }

    fun resetVisitValue() {
        visitEdit.putLong(Constants.VISIT_COUNTER, 0).apply()
    }

    fun saveAddedCarCounter() {
        val prevValue = fetchAddedCarCounter()
        val newValue = prevValue + 1
        addedEdit.putLong(Constants.ADDED_COUNTER, newValue).apply()
    }

    fun fetchAddedCarCounter(): Long {
        return countHowManyCarAdded.getLong(Constants.ADDED_COUNTER, 0)
    }

    fun resetAddedCounter() {
        addedEdit.putLong(Constants.ADDED_COUNTER, 0).apply()
    }
}