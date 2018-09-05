package com.babynames.names.domain.useCases

import com.babynames.core.domain.executors.PostExecutionThread
import com.babynames.core.domain.executors.ThreadExecutor
import com.babynames.core.domain.useCases.UseCase
import com.babynames.names.domain.entities.responseObjects.NameResponseObject
import com.babynames.names.domain.repositoryAbstractions.NamesRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetNamesUseCase @Inject constructor(private val namesRepository: NamesRepository, threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread) : UseCase<List<NameResponseObject>, String>(threadExecutor, postExecutionThread) {

    override fun createObservable(params: String): Observable<List<NameResponseObject>> =
            this.namesRepository.getNames(params)
}