package com.babynames.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface FavoritesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(favorites: List<FavoritesEntity>)

    @Query("DELETE FROM favorites WHERE id = :id")
    fun deleteFavorite(id: String)

    @Query("SELECT * FROM favorites ORDER BY id")
    fun getFavorites(): Flowable<List<FavoritesEntity>>
}