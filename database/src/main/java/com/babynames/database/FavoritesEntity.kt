package com.babynames.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoritesEntity(@PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id") val id: String,
                           @ColumnInfo(name = "name") val name: String,
                           @ColumnInfo(name = "drive") val gender: String,
                           @ColumnInfo(name = "meaning") val meaning: String,
                           @ColumnInfo(name = "origin") val origin: String,
                           @ColumnInfo(name = "icon") val icon: Int) {

}