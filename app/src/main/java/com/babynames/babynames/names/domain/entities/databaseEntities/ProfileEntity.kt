package com.babynames.babynames.names.domain.entities.databaseEntities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "profiles")
data class ProfileEntity(@ColumnInfo(name = "facebookId") val facebookId: String,
                         @ColumnInfo(name = "name") val name: String,
                         @ColumnInfo(name = "email") val email: String,
                         @ColumnInfo(name = "age") val age: Int,
                         @ColumnInfo(name = "gender") val gender: String,
                         @ColumnInfo(name = "picture") val picture: String,
                         @ColumnInfo(name = "coupleId") val coupleId: String) {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String? = null
}