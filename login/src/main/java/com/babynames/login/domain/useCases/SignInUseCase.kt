package com.babynames.login.domain.useCases

import com.babynames.core.domain.executors.PostExecutionThread
import com.babynames.core.domain.executors.ThreadExecutor
import com.babynames.core.domain.useCases.UseCase
import com.babynames.core.domain.entities.UserProfile
import com.babynames.login.domain.entities.requestObjects.SignInRequestObject
import com.babynames.login.domain.exceptions.SignInException
import com.babynames.login.domain.repositoryAbstractions.SignInRepository
import io.reactivex.Observable
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val signInRepository: SignInRepository, threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread) : UseCase<UserProfile, SignInRequestObject>(threadExecutor, postExecutionThread) {

    override fun createObservable(params: SignInRequestObject): Observable<UserProfile> =
            when {
                params.facebookId.isBlank() -> Observable.error(SignInException(SignInException.Type.FACEBOOK_ID_ERROR))
                else -> this.signInRepository.signIn(params)
            }

}