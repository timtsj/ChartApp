package com.example.android.chartapp

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.example.android.chartapp.data.di.networkModule
import com.example.android.chartapp.data.di.persistenceModule
import com.example.android.chartapp.domain.di.interactorModule
import com.example.android.chartapp.data.di.repositoryModule
import com.example.android.chartapp.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

/**
 * @author Timur Seisembayev
 * @since 29.02.2020
 */

class App : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(networkModule)
            modules(persistenceModule)
            modules(repositoryModule)
            modules(viewModelModule)
            modules(interactorModule)
        }
    }
}