package com.example.android.chartapp.presentation.feature.favorite

import com.example.android.chartapp.presentation.base.adapter.model.IAdapterItem

/**
 * @author Timur Seisembayev
 * @since 29.02.2020
 */

data class FavoriteDvo(
        val id: Long,
        val title: String
) : IAdapterItem