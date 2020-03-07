package com.example.android.chartapp.data.di

import androidx.preference.PreferenceManager
import androidx.room.Room
import com.example.android.chartapp.data.database.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * @author Timur Seisembayev
 * @since 29.02.2020
 */

val persistenceModule = module {

    single {
        Room
            .databaseBuilder(
                androidApplication(),
                AppDatabase::class.java,
                "chart_db"
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDatabase>().favoriteDao() }

    single { PreferenceManager.getDefaultSharedPreferences(androidContext()) }
}