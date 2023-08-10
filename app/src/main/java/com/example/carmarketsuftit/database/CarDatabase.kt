package com.example.carmarketsuftit.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.carmarketsuftit.model.Car

@Database(entities = [Car::class], version = 1, exportSchema = false)
abstract class CarDatabase : RoomDatabase() {
    abstract fun carDao(): CarDao
}