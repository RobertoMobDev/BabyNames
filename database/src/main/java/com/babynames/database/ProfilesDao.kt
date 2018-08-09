package com.babynames.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface ProfilesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProfile(profiles: List<ProfileEntity>)

    @Query("DELETE FROM profiles")
    fun deleteProfiles()

    @Query("SELECT * FROM profiles")
    fun getProfiles(): Flowable<List<ProfileEntity>>
}