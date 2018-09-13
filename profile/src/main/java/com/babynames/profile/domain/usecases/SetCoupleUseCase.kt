package com.babynames.profile.domain.usecases

import com.babynames.core.domain.executors.PostExecutionThread
import com.babynames.core.domain.executors.ThreadExecutor
import com.babynames.core.domain.useCases.UseCase
import com.babynames.profile.domain.entities.requestObjects.SetCoupleRequestObject
import com.babynames.profile.domain.entities.responseObjects.CoupleResponseObject
import com.babynames.profile.domain.repositoryabstractions.CoupleRepository
import io.reactivex.Observable
import javax.inject.Inject

class SetCoupleUseCase @Inject constructor(private val coupleRepository: CoupleRepository, threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread) : UseCase<CoupleResponseObject, SetCoupleRequestObject>(threadExecutor, postExecutionThread) {

    override fun createObservable(params: SetCoupleRequestObject): Observable<CoupleResponseObject> =
            this.coupleRepository.setCouple(params)
}