package com.gb.stopwatch.model

import android.app.Application
import com.gb.stopwatch.model.di.Koin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(Koin().getModule())
        }
    }

}