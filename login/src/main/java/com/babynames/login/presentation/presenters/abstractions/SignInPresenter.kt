package com.babynames.login.presentation.presenters.abstractions

import android.os.Bundle
import com.babynames.core.presentation.presenters.Presenter
import com.babynames.login.domain.entities.requestObjects.CheckPermissionsRequestObject
import com.babynames.login.domain.entities.requestObjects.CreateLocalAccountRequestObject
import com.babynames.login.domain.entities.requestObjects.SignInRequestObject
import com.babynames.login.presentation.viewModels.SignInViewModel

interface SignInPresenter : Presenter<SignInViewModel> {

    fun checkPermissions(checkPermissionsRequestObject: CheckPermissionsRequestObject)

    fun getFacebookInfo(bundle: Bundle)

    fun signIn(signInRequestObject: SignInRequestObject)

    fun createLocalAccount(createLocalAccountRequestObject: CreateLocalAccountRequestObject)

}