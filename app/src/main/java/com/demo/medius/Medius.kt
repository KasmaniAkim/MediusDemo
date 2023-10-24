package com.demo.medius

import android.app.Application
import android.content.Context
import com.demo.medius.data.di.appModule
import com.demo.medius.data.di.networkModule
import com.demo.medius.data.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class Medius : Application() {

    companion object {
        lateinit var instance: Medius
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        startKoin {
            androidContext(this@Medius)
            modules(listOf(appModule, networkModule, viewModelModule))
        }


    }

}