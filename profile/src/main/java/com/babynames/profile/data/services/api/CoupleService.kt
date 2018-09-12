package com.babynames.profile.data.services.api

import com.babynames.profile.domain.entities.responseObjects.CoupleResponseObject
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CoupleService {

    @GET("/BabyNames/Database.php")
    fun setCouple(@Query("type") type: String, @Query("user_id_1") user1: String, @Query("user_id_2") user2: String): Observable<CoupleResponseObject>
}