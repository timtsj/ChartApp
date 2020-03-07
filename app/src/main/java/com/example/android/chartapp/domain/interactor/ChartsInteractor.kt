package com.example.android.chartapp.domain.interactor

import com.example.android.chartapp.domain.base.BaseInteractor
import com.example.android.chartapp.domain.repository.ChartsRepository

/**
 * @author Timur Seisembayev
 * @since 29.02.2020
 */

class ChartsInteractor(
    private val chartsRepository: ChartsRepository
) : BaseInteractor() {

    fun getCharts(){
        chartsRepository.getCharts()
    }
}