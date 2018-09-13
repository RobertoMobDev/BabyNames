package com.babynames.profile.data.repositoryimplementations

import com.babynames.profile.data.datasourceimplementations.CoupleApiDataSourceImpl
import com.babynames.profile.domain.entities.requestObjects.GetCoupleRequestObject
import com.babynames.profile.domain.entities.requestObjects.SetCoupleRequestObject
import com.babynames.profile.domain.entities.responseObjects.CoupleResponseObject
import com.babynames.profile.domain.entities.responseObjects.GetCoupleResponseObject
import com.babynames.profile.domain.repositoryabstractions.CoupleRepository
import io.reactivex.Observable
import javax.inject.Inject

class CoupleRepositoryImpl @Inject constructor(private val coupleDataSourceImpl: CoupleApiDataSourceImpl) : CoupleRepository {

    override fun setCouple(setCoupleRequestObject: SetCoupleRequestObject): Observable<CoupleResponseObject> =
            this.coupleDataSourceImpl.setCouple(setCoupleRequestObject)

    override fun getCouple(getCoupleRequestObject: GetCoupleRequestObject): Observable<GetCoupleResponseObject> =
            this.coupleDataSourceImpl.getCouple(getCoupleRequestObject)
}