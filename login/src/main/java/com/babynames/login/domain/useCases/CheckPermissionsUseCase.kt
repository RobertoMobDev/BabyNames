package com.babynames.login.domain.useCases

import com.babynames.core.domain.executors.PostExecutionThread
import com.babynames.core.domain.executors.ThreadExecutor
import com.babynames.core.domain.useCases.UseCase
import com.babynames.login.domain.entities.requestObjects.CheckPermissionsRequestObject
import com.babynames.login.domain.exceptions.CheckPermissionsException
import io.reactivex.Observable
import javax.inject.Inject

class CheckPermissionsUseCase @Inject constructor(threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread) : UseCase<Boolean, CheckPermissionsRequestObject>(threadExecutor, postExecutionThread) {

    override fun createObservable(params: CheckPermissionsRequestObject): Observable<Boolean> =
            when {
                params.shouldRequestAccountPermissionRationale -> Observable.error(CheckPermissionsException(CheckPermissionsException.Type.NEED_REQUEST_ACCOUNT_PERMISSIONS_RATIONALE))
                !params.canAddLocalAccount -> Observable.error(CheckPermissionsException(CheckPermissionsException.Type.NEED_REQUEST_ACCOUNT_PERMISSIONS))
                params.accountManager.getAccountsByType(params.accountType).isNotEmpty() -> Observable.error(CheckPermissionsException(CheckPermissionsException.Type.ACCOUNT_ALREADY_EXIST_VALIDATION_ERROR))
                else -> Observable.just(true)
            }


}