package com.example.android.chartapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android.chartapp.data.database.dao.FavoriteDao
import com.example.android.chartapp.data.database.model.FavoriteDbModel

/**
 * @author Timur Seisembayev
 * @since 29.02.2020
 */

@Database(entities = [FavoriteDbModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao
}