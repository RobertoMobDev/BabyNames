package com.babynames.login.presentation.viewModels

import android.os.Bundle
import com.babynames.core.presentation.viewModels.BaseView
import com.babynames.core.domain.entities.UserProfile
import com.babynames.login.domain.entities.requestObjects.SignInRequestObject
import com.facebook.FacebookCallback
import com.facebook.login.LoginResult

interface SignInViewModel : BaseView, FacebookCallback<LoginResult> {

    fun displayFacebookInformationRecoveryError()

    fun checkPermissionsSuccess(boolean: Boolean)

    fun requestAccountPermissionRationale(requestCode: Int)

    fun requestAccountPermissions(requestCode: Int)

    fun displayErrorAddingAccountErrorMessage()

    fun displayExistingAccountErrorMessage()

    fun displayProfileNullErrorMessage()

    fun displayAccountTypeEmptyErrorMessage()

    fun onRecoveredFacebookInfoResponse(signInRequestObject: SignInRequestObject)

    fun onRecoverFacebookInfoError(error: String)

    fun onSignInSuccess(userProfile: UserProfile)

    fun onAccountCreated(userData: Bundle)

}