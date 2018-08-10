package com.babynames.login.presentation.presenters.abstractions

import com.babynames.core.presentation.presenters.Presenter
import com.babynames.login.domain.entities.requestObjects.CheckPermissionsRequestObject
import com.babynames.login.domain.entities.requestObjects.CreateLocalAccountRequestObject
import com.babynames.login.domain.entities.requestObjects.SignInRequestObject
import com.babynames.login.presentation.viewModels.SignInViewModel
import com.facebook.GraphRequest
import com.facebook.GraphResponse

interface SignInPresenter : Presenter<SignInViewModel> {

    fun checkPermissions(checkPermissionsRequestObject: CheckPermissionsRequestObject)

    fun getFacebookInfo(graphRequest: GraphRequest)

    fun signIn(signInRequestObject: SignInRequestObject)

    fun createLocalAccount(createLocalAccountRequestObject: CreateLocalAccountRequestObject)

}