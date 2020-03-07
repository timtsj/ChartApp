package com.example.android.chartapp.domain.di

import com.example.android.chartapp.domain.interactor.ChartsInteractor
import org.koin.dsl.module

/**
 * @author Timur Seisembayev
 * @since 29.02.2020
 */

val interactorModule = module {
    single { ChartsInteractor(get()) }
}