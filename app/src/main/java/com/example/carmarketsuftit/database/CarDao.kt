package com.example.carmarketsuftit.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.carmarketsuftit.model.Car

@Dao
interface CarDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCar(car: Car)

    @Insert
    fun addCars(cars: List<Car>)

    @Query("SELECT * FROM car_table")
    fun readAllData(): LiveData<List<Car>>

    @Query("SELECT * FROM car_table WHERE name LIKE :searchWord ORDER BY name ASC")
    fun getSearchCar(searchWord: String): LiveData<List<Car>>

//    @Query("SELECT COUNT(*) FROM car_table")
//    fun getRowCount(): LiveData<Int>

    @Query("SELECT (SELECT COUNT(*) FROM car_table) == 0")
    suspend fun isEmpty(): Boolean
}