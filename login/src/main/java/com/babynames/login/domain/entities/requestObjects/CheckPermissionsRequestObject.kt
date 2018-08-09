package com.babynames.login.domain.entities.requestObjects

import android.accounts.AccountManager

data class CheckPermissionsRequestObject(val accountManager: AccountManager,
                                         val accountType: String,
                                         val canAddLocalAccount: Boolean = false,
                                         val shouldRequestAccountPermissionRationale: Boolean = true)