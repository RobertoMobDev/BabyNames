package com.babynames.login.data.repositoryimplementations

import com.babynames.login.data.datasourceimplementations.SignInApiDataSourceImpl
import com.babynames.core.domain.entities.UserProfile
import com.babynames.login.domain.entities.requestObjects.SignInRequestObject
import com.babynames.login.domain.repositoryAbstractions.SignInRepository
import io.reactivex.Observable
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(private val signInApiDataSourceImpl: SignInApiDataSourceImpl) : SignInRepository {

    override fun signIn(requestObject: SignInRequestObject): Observable<UserProfile> = this.signInApiDataSourceImpl.signIn(requestObject)

}