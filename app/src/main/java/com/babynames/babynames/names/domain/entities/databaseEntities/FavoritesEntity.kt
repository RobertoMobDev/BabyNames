package com.babynames.babynames.names.domain.entities.databaseEntities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoritesEntity(@ColumnInfo(name = "name") val name: String,
                           @ColumnInfo(name = "drive") val gender: String,
                           @ColumnInfo(name = "meaning") val meaning: String,
                           @ColumnInfo(name = "origin") val origin: String,
                           @ColumnInfo(name = "icon") val icon: Int) {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String? = null
}