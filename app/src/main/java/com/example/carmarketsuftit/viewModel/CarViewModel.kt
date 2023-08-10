package com.example.carmarketsuftit.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.carmarketsuftit.model.Car
import com.example.carmarketsuftit.repository.CarRepository
import java.util.concurrent.Executors

class CarViewModel(private val repository: CarRepository) : ViewModel() {
    private var _isDataSaved = MutableLiveData<Boolean>()
    val isDataSaved: LiveData<Boolean>
        get() = _isDataSaved

    private val thread = Executors.newSingleThreadExecutor()

    fun addCar(car: Car) {
        thread.execute {
            repository.addCar(car)
        }
        thread.shutdown()
        _isDataSaved.postValue(thread.isShutdown)
    }

    fun getAllData(): LiveData<List<Car>> {
        return repository.readAllData()
    }

    fun getSearchCar(searchWord: String): LiveData<List<Car>> {
        return repository.getSearchCar(searchWord)
    }
}