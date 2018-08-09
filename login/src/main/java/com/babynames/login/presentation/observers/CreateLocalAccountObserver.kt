package com.babynames.login.presentation.observers

import android.os.Bundle
import com.babynames.core.presentation.observers.DefaultObserver
import com.babynames.login.domain.exceptions.CreateLocalAccountException
import com.babynames.login.presentation.viewModels.SignInViewModel

class CreateLocalAccountObserver(view: SignInViewModel?) : DefaultObserver<Bundle, SignInViewModel>(view) {

    override fun onComplete() {

    }

    override fun onNext(t: Bundle) {
        this.view?.onAccountCreated(t)
    }

    override fun onError(e: Throwable) {
        if (e is CreateLocalAccountException) {
            when (e.validationType) {
                CreateLocalAccountException.Type.ACCOUNT_TYPE_IS_NULL_OR_EMPTY -> {
                    this.view?.displayAccountTypeEmptyErrorMessage()
                }
                CreateLocalAccountException.Type.UNABLE_TO_CREATE_ACCOUNT_ERROR -> {
                    this.view?.displayErrorAddingAccountErrorMessage()
                }
            }
        } else {
            this.view?.displayError(e.localizedMessage)
        }
    }
}