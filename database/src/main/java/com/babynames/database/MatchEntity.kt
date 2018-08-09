package com.babynames.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "matches")
data class MatchEntity(@ColumnInfo(name = "match") val matchName: String) {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String? = null
}