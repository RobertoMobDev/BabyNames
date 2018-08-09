package com.babynames.login.data.services.api

import com.babynames.login.domain.entities.responseObjects.CreateAccountResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SignInService {

    @GET("/BabyNames/Database.php")
    fun createAccount(@Query("type") type: String, @Query("facebook_id") id: String, @Query("email") email: String,
                      @Query("profile_image") picture: String, @Query("gender") gender: String, @Query("age") age: String,
                      @Query("name") name: String): Observable<CreateAccountResponse>
}