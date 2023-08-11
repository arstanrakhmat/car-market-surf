package com.example.carmarketsuftit.repository

import androidx.lifecycle.LiveData
import com.example.carmarketsuftit.database.CarDao
import com.example.carmarketsuftit.model.Car

class CarRepository(private val carDao: CarDao) {
    fun readAllData(): LiveData<List<Car>> {
        return carDao.readAllData()
    }

    fun addCar(car: Car) {
        carDao.addCar(car)
    }

    fun addCars(cars: List<Car>) {
        carDao.addCars(cars)
    }

    fun getSearchCar(searchWord: String): LiveData<List<Car>> {
        return carDao.getSearchCar(searchWord)
    }

//    fun getRowCount(): LiveData<Int> {
//        return carDao.getRowCount()
//    }

    suspend fun isEmpty(): Boolean {
        return carDao.isEmpty()
    }
}