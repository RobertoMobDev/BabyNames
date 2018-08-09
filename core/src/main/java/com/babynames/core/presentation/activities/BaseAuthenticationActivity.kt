package com.babynames.core.presentation.activities

import android.Manifest
import android.accounts.Account
import android.accounts.AccountManager
import android.accounts.OnAccountsUpdateListener
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import com.babynames.core.R
import com.babynames.core.domain.entities.UserProfile
import com.babynames.core.presentation.account.AccountsManager
import com.babynames.core.presentation.account.AccountsManager.REQUEST_CODE_FOR_REMOVE_ACCOUNT
import com.babynames.core.presentation.account.AccountsManager.REQUEST_PERMISSIONS_CODE
import com.babynames.core.presentation.managers.PermissionsManager
import org.jetbrains.anko.alert
import org.jetbrains.anko.cancelButton
import org.jetbrains.anko.okButton

/**
 * Created by jesusmanuelramosjuarez on 17/01/18.
 */
abstract class BaseAuthenticationActivity : BaseActivity(), OnAccountsUpdateListener {

    private var _userProfile: UserProfile? = null

    val userProfile: UserProfile?
        get() = this._userProfile

    abstract fun getUserProfile(userProfile: UserProfile)

    private var isAccountListenerSet: Boolean = false

    override fun onStart() {
        super.onStart()
        if (!isAccountListenerSet) {
            addAccountUpdateListener(this, REQUEST_PERMISSIONS_CODE)
        }
    }

    override fun onStop() {
        super.onStop()
        if (isAccountListenerSet) {
            AccountManager.get(this).removeOnAccountsUpdatedListener(this)
            this.isAccountListenerSet = false
        }
    }

    override fun onAccountsUpdated(accounts: Array<out Account>?) {

        if (AccountsManager.accountExistByType(this.packageName, accounts ?: emptyArray())) {

            if (this._userProfile == null) {

                val account: Account = AccountsManager.getAccountByType(this.packageName, accounts
                        ?: emptyArray())!!

                val accountManager: AccountManager = AccountManager.get(this)

                val id: String = accountManager.getUserData(account, "id") ?: ""
                val facebookId: String = accountManager.getUserData(account, "facebookId") ?: ""
                val email: String = accountManager.getUserData(account, "email") ?: ""
                val profileImage: String = accountManager.getUserData(account, "profileImage") ?: ""
                val premium: String = accountManager.getUserData(account, "premium") ?: ""
                val gender: String = accountManager.getUserData(account, "gender") ?: ""
                val age: String = accountManager.getUserData(account, "age") ?: ""
                val name: String = accountManager.getUserData(account, "name") ?: ""
                val coupleId: String = accountManager.getUserData(account, "coupleId") ?: ""


                if (id.isBlank()) {
                    this.removeLocalAccount()
                } else {
                    this._userProfile = UserProfile(id, facebookId, email, profileImage, premium, gender, age, name, coupleId)
                    this.getUserProfile(this._userProfile!!)
                }
            }
        } else {
            AccountManager.get(this).addAccount(packageName, null, null, null, this, null, null)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_PERMISSIONS_CODE -> {
                if (PermissionsManager.verifyPermissions(grantResults)) {
                    if (this.isAccountListenerSet) {
                        AccountManager.get(this).removeOnAccountsUpdatedListener(this)
                    }
                    addAccountUpdateListener(this, REQUEST_PERMISSIONS_CODE)
                } else {
                    alert {
                        message = getString(R.string.splash_text_msg_permision)
                        isCancelable = false
                        okButton {
                            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                    Uri.fromParts("package", packageName, null))
                            startActivity(intent)
                            finish()
                        }
                    }.show()
                }
            }
            REQUEST_CODE_FOR_REMOVE_ACCOUNT -> if (PermissionsManager.verifyPermissions(grantResults)) {
                removeLocalAccount()
            }

        }
    }

    private fun addAccountUpdateListener(activity: Activity, requestCode: Int) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.GET_ACCOUNTS) == PackageManager.PERMISSION_GRANTED) {
            AccountManager.get(this).addOnAccountsUpdatedListener(this, null, true)
            this.isAccountListenerSet = true
        } else {
            requestAccountPermissions(activity, requestCode)
        }
    }

    private fun requestAccountPermissions(activity: Activity, requestCode: Int) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.GET_ACCOUNTS)) {
            alert {
                message = getString(R.string.required_permissions_message)
                isCancelable = false
                okButton {
                    ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.GET_ACCOUNTS), requestCode)
                }
                cancelButton {
                    finish()
                }
            }.show()
        } else {
            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.GET_ACCOUNTS), requestCode)
        }
    }

    fun removeLocalAccount() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.GET_ACCOUNTS) == PackageManager.PERMISSION_GRANTED) {
            val accountManager = AccountManager.get(this@BaseAuthenticationActivity)
            if (AccountsManager.accountExistByType(packageName, accountManager.getAccountsByType(packageName))) {
                val account = accountManager.getAccountsByType(packageName)[0]
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                    accountManager.removeAccount(account, this@BaseAuthenticationActivity, null, null)
                } else {
                    accountManager.removeAccount(account, null, null)
                }
            }
        } else {
            requestAccountPermissions(this, REQUEST_CODE_FOR_REMOVE_ACCOUNT)
        }
    }
}