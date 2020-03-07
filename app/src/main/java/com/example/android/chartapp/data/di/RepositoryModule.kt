package com.example.android.chartapp.data.di

import com.example.android.chartapp.data.repository.ChartsRepositoryImpl
import com.example.android.chartapp.domain.repository.ChartsRepository
import org.koin.dsl.module

/**
 * @author Timur Seisembayev
 * @since 29.02.2020
 */

val repositoryModule = module {
    single<ChartsRepository> { ChartsRepositoryImpl(get()) }
}