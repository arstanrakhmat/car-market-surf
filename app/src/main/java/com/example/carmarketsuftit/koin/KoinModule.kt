package com.example.carmarketsuftit.koin

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.carmarketsuftit.database.CarDao
import com.example.carmarketsuftit.database.CarDatabase
import com.example.carmarketsuftit.database.MY_DATA_BASE
import com.example.carmarketsuftit.repository.CarRepository
import com.example.carmarketsuftit.utils.CustomPreferences
import com.example.carmarketsuftit.utils.getStaticCar
import com.example.carmarketsuftit.viewModel.CarViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(get(), CarDatabase::class.java, MY_DATA_BASE)
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    GlobalScope.launch(Dispatchers.IO) {
                        val carDao = get<CarDao>()
                        val staticData = getStaticCar(androidContext())
                        carDao.addCars(staticData)
                    }
                }
            })
            .build()
    }
    single { get<CarDatabase>().carDao() }
    single { CarRepository(carDao = get()) }
    single { CustomPreferences(androidApplication()) }
}

val viewModels = module {
    viewModel { CarViewModel(repository = get()) }
}