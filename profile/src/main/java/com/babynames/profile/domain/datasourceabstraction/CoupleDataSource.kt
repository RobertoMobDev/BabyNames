package com.babynames.profile.domain.datasourceabstraction

import com.babynames.profile.domain.entities.responseObjects.CoupleResponseObject
import io.reactivex.Observable

interface CoupleDataSource {
    fun setCouple(type: String, user1: String, user2: String): Observable<CoupleResponseObject>
}