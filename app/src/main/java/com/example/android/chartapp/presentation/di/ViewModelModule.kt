package com.example.android.chartapp.presentation.di

import com.example.android.chartapp.presentation.feature.charts.ChartsViewModel
import com.example.android.chartapp.presentation.feature.chartsDetail.ChartDetailViewModel
import com.example.android.chartapp.presentation.feature.favorite.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Timur Seisembayev
 * @since 29.02.2020
 */

val viewModelModule = module {
    viewModel { ChartsViewModel(get()) }
    viewModel { FavoriteViewModel() }
    viewModel { ChartDetailViewModel() }
}