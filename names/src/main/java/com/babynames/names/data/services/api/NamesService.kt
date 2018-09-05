package com.babynames.names.data.services.api

import com.babynames.names.domain.entities.responseObjects.NamesResponseObject
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NamesService {

    @GET("/BabyNames/Database.php")
    fun getNames(@Query("type") type: String): Observable<NamesResponseObject>

}