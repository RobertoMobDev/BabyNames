package com.babynames.database.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.babynames.database.FavoritesDao
import com.babynames.database.FavoritesEntity

@Database(entities = [FavoritesEntity::class], version = 1, exportSchema = false)
abstract class NamesDatabase : RoomDatabase() {

    abstract fun getFavoritesDao(): FavoritesDao

}