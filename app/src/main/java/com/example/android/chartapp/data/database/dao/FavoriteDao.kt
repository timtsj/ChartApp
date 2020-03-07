package com.example.android.chartapp.data.database.dao

import androidx.room.*
import com.example.android.chartapp.data.database.model.FavoriteDbModel

/**
 * @author Timur Seisembayev
 * @since 29.02.2020
 */

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favorites: List<FavoriteDbModel>)

    @Query("SELECT * FROM FavoriteDbModel WHERE id = :id")
    fun getNews(id: Long): FavoriteDbModel

    @Query("SELECT * FROM FavoriteDbModel")
    fun getNewsList(): List<FavoriteDbModel>

    @Update
    fun update(favorite: FavoriteDbModel)

    @Query("DELETE FROM FavoriteDbModel")
    fun clearAll()
}