package com.babynames.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface MatchesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMatch(match: List<MatchEntity>)

    @Query("DELETE FROM matches WHERE id = :id")
    fun delete(id: Int)

    @Query("SELECT * FROM matches ORDER BY id")
    fun getMatches(): Flowable<List<MatchEntity>>
}