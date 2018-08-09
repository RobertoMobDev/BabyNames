package com.babynames.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "genders")
data class GenderEntity(@ColumnInfo(name = "gender") val genderSelected: String) {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val genderId: Int? = null
}