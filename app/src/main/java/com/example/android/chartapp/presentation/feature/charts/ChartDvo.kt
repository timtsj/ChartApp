package com.example.android.chartapp.presentation.feature.charts

import com.example.android.chartapp.presentation.base.adapter.model.IAdapterItem

/**
 * @author Timur Seisembayev
 * @since 28.02.2020
 */

data class ChartDvo(
        val id: Long,
        val title: String
) : IAdapterItem