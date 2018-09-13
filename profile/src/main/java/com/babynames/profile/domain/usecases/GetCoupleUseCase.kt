package com.babynames.profile.domain.usecases

import com.babynames.core.domain.executors.PostExecutionThread
import com.babynames.core.domain.executors.ThreadExecutor
import com.babynames.core.domain.useCases.UseCase
import com.babynames.profile.domain.entities.requestObjects.GetCoupleRequestObject
import com.babynames.profile.domain.entities.responseObjects.GetCoupleResponseObject
import com.babynames.profile.domain.repositoryabstractions.CoupleRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetCoupleUseCase @Inject constructor(private val coupleRepository: CoupleRepository, threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread) : UseCase<GetCoupleResponseObject, GetCoupleRequestObject>(threadExecutor, postExecutionThread) {

    override fun createObservable(params: GetCoupleRequestObject): Observable<GetCoupleResponseObject> =
            this.coupleRepository.getCouple(params)
}