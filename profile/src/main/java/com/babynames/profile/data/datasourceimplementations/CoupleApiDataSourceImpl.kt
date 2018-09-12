package com.babynames.profile.data.datasourceimplementations

import com.babynames.profile.data.services.api.CoupleService
import com.babynames.profile.domain.datasourceabstraction.CoupleDataSource
import com.babynames.profile.domain.entities.responseObjects.CoupleResponseObject
import io.reactivex.Observable
import javax.inject.Inject

class CoupleApiDataSourceImpl @Inject constructor(private val coupleService: CoupleService) : CoupleDataSource {

    override fun setCouple(type: String, user1: String, user2: String): Observable<CoupleResponseObject> =
            this.coupleService.setCouple(type, user1, user2).map {
                if (it.success == "Couple created" || it.success == "Couple updated") {
                    it
                } else {
                    //Excepcion personalizada
                    throw ExceptionInInitializerError()
                }
            }

}