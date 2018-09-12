package com.babynames.profile.data.repositoryimplementations

import com.babynames.profile.data.datasourceimplementations.CoupleApiDataSourceImpl
import com.babynames.profile.domain.entities.responseObjects.CoupleResponseObject
import com.babynames.profile.domain.repositoryabstractions.CoupleRepository
import io.reactivex.Observable
import javax.inject.Inject

class CoupleRepositoryImpl @Inject constructor(private val coupleDataSourceImpl: CoupleApiDataSourceImpl) : CoupleRepository {

    override fun setCouple(type: String, user1: String, user2: String): Observable<CoupleResponseObject> =
            this.coupleDataSourceImpl.setCouple(type, user1, user2)
}