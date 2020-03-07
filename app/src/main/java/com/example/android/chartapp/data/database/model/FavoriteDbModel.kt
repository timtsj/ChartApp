package com.example.android.chartapp.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Timur Seisembayev
 * @since 29.02.2020
 */

@Entity
data class FavoriteDbModel(
        @PrimaryKey val id: Long,
        val title: String,
        val url: String
)