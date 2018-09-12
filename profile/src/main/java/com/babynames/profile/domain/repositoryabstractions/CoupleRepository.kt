package com.babynames.profile.domain.repositoryabstractions

import com.babynames.profile.domain.entities.responseObjects.CoupleResponseObject
import io.reactivex.Observable

interface CoupleRepository {
    fun setCouple(type: String, user1: String, user2: String): Observable<CoupleResponseObject>
}