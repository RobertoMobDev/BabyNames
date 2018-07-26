package com.babynames.babynames.core.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.babynames.babynames.names.domain.entities.databaseEntities.FavoritesEntity
import com.babynames.babynames.names.domain.entities.databaseEntities.GenderEntity
import com.babynames.babynames.names.domain.entities.databaseEntities.ProfileEntity

@Database(entities = [ProfileEntity::class, FavoritesEntity::class, GenderEntity::class], version = 1, exportSchema = false)
abstract class NamesDatabase : RoomDatabase() {
}