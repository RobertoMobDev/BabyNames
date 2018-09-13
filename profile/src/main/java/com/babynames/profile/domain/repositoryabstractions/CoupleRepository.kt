package com.babynames.profile.domain.repositoryabstractions

import com.babynames.profile.domain.entities.requestObjects.GetCoupleRequestObject
import com.babynames.profile.domain.entities.requestObjects.SetCoupleRequestObject
import com.babynames.profile.domain.entities.responseObjects.CoupleResponseObject
import com.babynames.profile.domain.entities.responseObjects.GetCoupleResponseObject
import io.reactivex.Observable

interface CoupleRepository {
    fun setCouple(setCoupleRequestObject: SetCoupleRequestObject): Observable<CoupleResponseObject>

    fun getCouple(getCoupleRequestObject: GetCoupleRequestObject): Observable<GetCoupleResponseObject>
}