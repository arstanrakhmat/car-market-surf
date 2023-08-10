package com.example.carmarketsuftit.application

import android.app.Application
import com.example.carmarketsuftit.koin.databaseModule
import com.example.carmarketsuftit.koin.viewModels
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(databaseModule, viewModels)
            androidContext(this@MyApplication)
        }
    }
}