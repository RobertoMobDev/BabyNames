package com.babynames.login.domain.entities.requestObjects

import android.accounts.AccountManager
import com.babynames.core.domain.entities.UserProfile

data class CreateLocalAccountRequestObject(
        val accountType: String,
        val accountManager: AccountManager,
        val userProfile: UserProfile
)