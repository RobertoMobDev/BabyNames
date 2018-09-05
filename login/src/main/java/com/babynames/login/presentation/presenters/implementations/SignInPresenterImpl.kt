package com.babynames.login.presentation.presenters.implementations

import com.babynames.core.presentation.presenters.AbsPresenter
import com.babynames.login.domain.entities.requestObjects.CheckPermissionsRequestObject
import com.babynames.login.domain.entities.requestObjects.CreateLocalAccountRequestObject
import com.babynames.login.domain.entities.requestObjects.SignInRequestObject
import com.babynames.login.domain.useCases.CheckPermissionsUseCase
import com.babynames.login.domain.useCases.CreateLocalAccountUseCase
import com.babynames.login.domain.useCases.RecoverFacebookInfoUseCase
import com.babynames.login.domain.useCases.SignInUseCase
import com.babynames.login.presentation.observers.CheckPermissionsObserver
import com.babynames.login.presentation.observers.CreateLocalAccountObserver
import com.babynames.login.presentation.observers.RecoverFacebookInfoObserver
import com.babynames.login.presentation.observers.SignInObserver
import com.babynames.login.presentation.presenters.abstractions.SignInPresenter
import com.babynames.login.presentation.viewModels.SignInViewModel
import com.facebook.GraphRequest
import javax.inject.Inject

class SignInPresenterImpl @Inject constructor(private val checkPermissionsUseCase: CheckPermissionsUseCase,
                                              private val facebookInfoUseCase: RecoverFacebookInfoUseCase,
                                              private val signInUseCase: SignInUseCase,
                                              private val createLocalAccountUseCase: CreateLocalAccountUseCase) : AbsPresenter<SignInViewModel>(), SignInPresenter {

    override fun onBind(view: SignInViewModel) {

    }

    override fun onDestroy() {
        super.onDestroy()
        this.checkPermissionsUseCase.dispose()
        this.facebookInfoUseCase.dispose()
        this.signInUseCase.dispose()
        this.createLocalAccountUseCase.dispose()
    }

    override fun checkPermissions(checkPermissionsRequestObject: CheckPermissionsRequestObject) {
        this.checkPermissionsUseCase.execute(CheckPermissionsObserver(this.view), checkPermissionsRequestObject)
    }

    override fun getFacebookInfo(graphRequest: GraphRequest) {
        this.facebookInfoUseCase.execute(RecoverFacebookInfoObserver(this.view), graphRequest)
    }

    override fun signIn(signInRequestObject: SignInRequestObject) {
        this.signInUseCase.execute(SignInObserver(this.view), signInRequestObject)
    }

    override fun createLocalAccount(createLocalAccountRequestObject: CreateLocalAccountRequestObject) {
        this.createLocalAccountUseCase.execute(CreateLocalAccountObserver(this.view), createLocalAccountRequestObject)
    }
}