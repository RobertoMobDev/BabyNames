package com.babynames.login.domain.repositoryAbstractions

import com.babynames.core.domain.entities.UserProfile
import com.babynames.login.domain.entities.requestObjects.SignInRequestObject
import io.reactivex.Observable

interface SignInRepository {

    fun signIn(requestObject: SignInRequestObject): Observable<UserProfile>

}