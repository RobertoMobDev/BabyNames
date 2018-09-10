package com.babynames.babynames

import android.Manifest
import android.accounts.AccountManager
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import com.babynames.babynames.presentation.components.DaggerSplashComponent
import com.babynames.core.presentation.account.AccountsManager
import com.babynames.core.presentation.getApplicationComponent
import com.babynames.core.presentation.managers.PermissionsManager
import com.babynames.gender.presentation.GenderActivity
import com.babynames.login.presentation.WelcomeActivity
import com.facebook.login.LoginManager
import com.ia.mchaveza.kotlin_library.SharedPreferencesManager
import org.jetbrains.anko.accountManager
import org.jetbrains.anko.alert
import org.jetbrains.anko.cancelButton
import org.jetbrains.anko.okButton
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var sharedPreferencesManager: SharedPreferencesManager

    private val splashComponent by lazy {
        DaggerSplashComponent.builder()
                .applicationComponent(this.getApplicationComponent())
                .build()
    }

    private val GENDER_SELECTED = "gender"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.splashComponent.inject(this)

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.GET_ACCOUNTS) == PackageManager.PERMISSION_GRANTED || Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            this.manageIntent(this.accountManager)
        } else {
            this.requestAccountPermissions(this, AccountsManager.REQUEST_PERMISSIONS_CODE)
        }
    }

    private fun manageIntent(accountManager: AccountManager) {
        if (accountManager.getAccountsByType(this.packageName).isEmpty()) {
            val intent = Intent(this, WelcomeActivity::class.java)
            LoginManager.getInstance().logOut()
            startActivityForResult(intent, AccountsManager.REQUEST_CODE_SIGN_IN)
        } else {
            if (sharedPreferencesManager.getSharedPreference(GENDER_SELECTED, "").isNotEmpty()) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, GenderActivity::class.java)
                intent.putExtra("isGender", false)
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            AccountsManager.REQUEST_PERMISSIONS_CODE -> {
                if (PermissionsManager.verifyPermissions(grantResults)) {
                    this.manageIntent(this.accountManager)
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
            else -> {

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AccountsManager.REQUEST_CODE_SIGN_IN && resultCode == AccountsManager.RESULT_CODE_ACCOUNT_CREATED) {
            if (sharedPreferencesManager.getSharedPreference(GENDER_SELECTED, "").isNotEmpty()) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, GenderActivity::class.java)
                startActivity(intent)
                finish()
            }
        } else {
            finish()
        }
    }

    private fun requestAccountPermissions(activity: Activity, requestCode: Int) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.GET_ACCOUNTS) || Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1) {
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


}