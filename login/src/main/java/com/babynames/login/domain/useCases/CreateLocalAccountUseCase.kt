package com.babynames.login.domain.useCases

import android.accounts.Account
import android.os.Bundle
import com.babynames.core.domain.executors.PostExecutionThread
import com.babynames.core.domain.executors.ThreadExecutor
import com.babynames.core.domain.useCases.UseCase
import com.babynames.login.domain.entities.requestObjects.CreateLocalAccountRequestObject
import com.babynames.login.domain.exceptions.CreateLocalAccountException
import io.reactivex.Observable
import javax.inject.Inject

class CreateLocalAccountUseCase @Inject constructor(threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread) : UseCase<Bundle, CreateLocalAccountRequestObject>(threadExecutor, postExecutionThread) {

    override fun createObservable(params: CreateLocalAccountRequestObject): Observable<Bundle> =
            if (params.accountType.isBlank()) {
                Observable.error(CreateLocalAccountException(CreateLocalAccountException.Type.ACCOUNT_TYPE_IS_NULL_OR_EMPTY))
            } else {
                val account = Account(params.userProfile.email, params.accountType)
                if (params.accountManager.addAccountExplicitly(account, params.userProfile.facebookId, params.userProfile.bundle)) {
                    Observable.just(params.userProfile.bundle)
                } else {
                    Observable.error(CreateLocalAccountException(CreateLocalAccountException.Type.UNABLE_TO_CREATE_ACCOUNT_ERROR))
                }
            }
}