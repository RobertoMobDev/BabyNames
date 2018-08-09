package com.babynames.login.presentation.observers

import com.babynames.core.presentation.observers.DefaultObserver
import com.babynames.core.domain.entities.UserProfile
import com.babynames.login.domain.exceptions.SignInException
import com.babynames.login.presentation.viewModels.SignInViewModel

class SignInObserver(view: SignInViewModel?) : DefaultObserver<UserProfile, SignInViewModel>(view) {

    override fun onComplete() {

    }

    override fun onNext(t: UserProfile) {
        this.view?.onSignInSuccess(t)
    }

    override fun onError(e: Throwable) {
        if (e is SignInException) {
            when (e.validationType) {
                SignInException.Type.PROFILE_NULL_ERROR -> {
                    this.view?.displayProfileNullErrorMessage()
                }
                else -> this.view?.displayError(e.localizedMessage)
            }
        } else {
            this.view?.displayError(e.localizedMessage)
        }
    }
}