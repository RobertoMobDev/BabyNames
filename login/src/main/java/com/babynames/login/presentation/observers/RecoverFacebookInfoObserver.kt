package com.babynames.login.presentation.observers

import com.babynames.core.presentation.observers.DefaultObserver
import com.babynames.login.domain.entities.requestObjects.SignInRequestObject
import com.babynames.login.domain.exceptions.SignInException
import com.babynames.login.presentation.viewModels.SignInViewModel

class RecoverFacebookInfoObserver(view: SignInViewModel?) : DefaultObserver<SignInRequestObject, SignInViewModel>(view) {

    override fun onComplete() {

    }

    override fun onNext(t: SignInRequestObject) {
        this.view?.onRecoveredFacebookInfoResponse(t)
    }

    override fun onError(e: Throwable) {
        if (e is SignInException) {
            when (e.validationType) {
                SignInException.Type.FACEBOOK_GRAPH_ERROR -> {
                    this.view?.onRecoverFacebookInfoError(e.localizedMessage)
                }
                else -> {
                    this.view?.displayError(e.localizedMessage)
                }
            }
        } else {
            this.view?.displayError(e.localizedMessage)
        }
    }
}