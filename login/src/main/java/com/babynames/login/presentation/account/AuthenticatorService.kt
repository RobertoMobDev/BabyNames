package com.babynames.login.presentation.account

import android.app.Service
import android.content.Intent
import android.os.IBinder

class AuthenticatorService : Service() {

    private val authenticator: Authenticator by lazy { Authenticator(this) }

    override fun onBind(p0: Intent?): IBinder {
        return this.authenticator.iBinder
    }

}