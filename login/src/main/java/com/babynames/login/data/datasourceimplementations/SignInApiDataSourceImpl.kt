package com.babynames.login.data.datasourceimplementations

import com.babynames.login.data.services.api.SignInService
import com.babynames.login.domain.datasourceAbstraction.SignInDataSource
import com.babynames.core.domain.entities.UserProfile
import com.babynames.login.domain.entities.requestObjects.SignInRequestObject
import com.babynames.login.domain.exceptions.SignInException
import io.reactivex.Observable
import javax.inject.Inject

class SignInApiDataSourceImpl @Inject constructor(private val signInService: SignInService) : SignInDataSource {
    override fun signIn(requestObject: SignInRequestObject): Observable<UserProfile> =
            this.signInService.createAccount(requestObject.type, requestObject.facebookId, requestObject.email, requestObject.picture,
                    requestObject.gender, requestObject.age, requestObject.name).map {
                if (it.data.isEmpty()) {
                    throw SignInException(SignInException.Type.PROFILE_NULL_ERROR)
                } else {
                    val p = it.data.first()
                    UserProfile(p.id, p.facebookId, p.email, p.profileImage, p.premium, p.gender, p.age, p.name, p.coupleId)
                }
            }
}