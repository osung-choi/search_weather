package com.osung.idustest

import android.app.Application
import com.osung.idustest.di.dataModule
import com.osung.idustest.di.networkModule
import com.osung.idustest.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(networkModule, viewModelModule, dataModule)
        }
    }
}