package com.babynames.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [ProfileEntity::class, FavoritesEntity::class, GenderEntity::class, MatchEntity::class], version = 1, exportSchema = false)
abstract class NamesDatabase : RoomDatabase() {
    abstract fun getProfilesDao(): ProfilesDao
    abstract fun getFavoritesDao(): FavoritesDao
    abstract fun getMatchesDao(): MatchesDao
}