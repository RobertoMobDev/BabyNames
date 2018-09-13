package com.babynames.profile.data.datasourceimplementations

import com.babynames.profile.data.services.api.CoupleService
import com.babynames.profile.domain.datasourceabstraction.CoupleDataSource
import com.babynames.profile.domain.entities.requestObjects.GetCoupleRequestObject
import com.babynames.profile.domain.entities.requestObjects.SetCoupleRequestObject
import com.babynames.profile.domain.entities.responseObjects.CoupleResponseObject
import com.babynames.profile.domain.entities.responseObjects.GetCoupleResponseObject
import com.babynames.profile.domain.exceptions.GetCoupleException
import com.babynames.profile.domain.exceptions.SetCoupleException
import io.reactivex.Observable
import javax.inject.Inject

class CoupleApiDataSourceImpl @Inject constructor(private val coupleService: CoupleService) : CoupleDataSource {

    override fun setCouple(setCoupleRequestObject: SetCoupleRequestObject): Observable<CoupleResponseObject> =
            this.coupleService.setCouple(setCoupleRequestObject.type, setCoupleRequestObject.user1, setCoupleRequestObject.user2).map {
                if (it.success == "Couple created" || it.success == "Couple updated") {
                    it
                } else {
                    throw SetCoupleException(SetCoupleException.Type.SET_COUPLE_ERROR)
                }
            }


    override fun getCouple(getCoupleRequestObject: GetCoupleRequestObject): Observable<GetCoupleResponseObject> =
            this.coupleService.getCouple(getCoupleRequestObject.type, getCoupleRequestObject.coupleId).map {
                if (it.success == "Couple found") {
                    it
                } else {
                    throw GetCoupleException(GetCoupleException.Type.GET_COUPLE_ERROR)
                }
            }
}