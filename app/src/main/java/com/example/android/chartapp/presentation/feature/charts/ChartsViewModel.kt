package com.example.android.chartapp.presentation.feature.charts

import com.example.android.chartapp.domain.interactor.ChartsInteractor
import com.example.android.chartapp.presentation.base.BaseViewModel

/**
 * @author Timur Seisembayev
 * @since 29.02.2020
 */

class ChartsViewModel(
    private val chartsInteractor: ChartsInteractor
) : BaseViewModel() {

    fun get() {
        chartsInteractor.getCharts()
    }

}