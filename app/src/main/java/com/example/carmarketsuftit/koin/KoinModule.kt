package com.example.carmarketsuftit.koin

import androidx.room.Room
import com.example.carmarketsuftit.database.CarDatabase
import com.example.carmarketsuftit.repository.CarRepository
import com.example.carmarketsuftit.viewModel.CarViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {
    single { Room.databaseBuilder(get(), CarDatabase::class.java, "car_database") }
    single { get<CarDatabase>().carDao() }
    factory { CarRepository(carDao = get()) }
}

val viewModel = module {
    viewModel { CarViewModel(repository = get()) }
}