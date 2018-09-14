package com.babynames.names.domain.useCases

import com.babynames.core.domain.executors.PostExecutionThread
import com.babynames.core.domain.executors.ThreadExecutor
import com.babynames.core.domain.useCases.UseCase
import com.babynames.names.domain.entities.Name
import com.babynames.names.domain.repositoryAbstractions.NamesRepository
import com.ia.mchaveza.kotlin_library.SharedPreferencesManager
import io.reactivex.Observable
import javax.inject.Inject

class GetNamesUseCase @Inject constructor(private val namesRepository: NamesRepository, private val sharedPreferencesManager: SharedPreferencesManager, threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread) : UseCase<List<Name>, String>(threadExecutor, postExecutionThread) {

    private val GENDER_SELECTED = "gender"

    override fun createObservable(params: String): Observable<List<Name>> =
            this.namesRepository.getNames(params).map {
                it.filter { name ->
                    sharedPreferencesManager.getSharedPreference(GENDER_SELECTED, "") == "unisex" ||
                            (name.gender == "unisex" || name.gender == sharedPreferencesManager.getSharedPreference(GENDER_SELECTED, ""))
                }
            }
}