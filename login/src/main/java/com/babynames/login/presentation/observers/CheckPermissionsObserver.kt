package com.babynames.login.presentation.observers

import com.babynames.core.presentation.account.AccountsManager
import com.babynames.core.presentation.observers.DefaultObserver
import com.babynames.login.domain.exceptions.CheckPermissionsException
import com.babynames.login.presentation.viewModels.SignInViewModel

class CheckPermissionsObserver(view: SignInViewModel?) : DefaultObserver<Boolean, SignInViewModel>(view) {

    override fun onComplete() {

    }

    override fun onNext(t: Boolean) {
        this.view?.checkPermissionsSuccess(t)
    }

    override fun onError(e: Throwable) {
        if (e is CheckPermissionsException) {
            when (e.validationType) {
                CheckPermissionsException.Type.NEED_REQUEST_ACCOUNT_PERMISSIONS -> {
                    this.view?.requestAccountPermissions(AccountsManager.REQUEST_PERMISSIONS_CODE)
                }
                CheckPermissionsException.Type.NEED_REQUEST_ACCOUNT_PERMISSIONS_RATIONALE -> {
                    this.view?.requestAccountPermissionRationale(AccountsManager.REQUEST_PERMISSIONS_CODE)
                }
                CheckPermissionsException.Type.ACCOUNT_ALREADY_EXIST_VALIDATION_ERROR -> {
                    this.view?.displayExistingAccountErrorMessage()
                }

                else -> this.view?.displayError(e.localizedMessage)
            }
        } else {
            this.view?.displayError(e.localizedMessage)
        }
    }
}