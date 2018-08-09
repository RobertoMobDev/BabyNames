package com.babynames.login.domain.datasourceAbstraction

import com.babynames.core.domain.entities.UserProfile
import com.babynames.login.domain.entities.requestObjects.SignInRequestObject
import io.reactivex.Observable

interface SignInDataSource {
    fun signIn(requestObject: SignInRequestObject): Observable<UserProfile>
}