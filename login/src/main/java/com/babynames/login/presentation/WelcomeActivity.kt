package com.babynames.login.presentation

import android.Manifest
import android.accounts.AccountAuthenticatorResponse
import android.accounts.AccountManager
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.LinearLayout
import com.babynames.core.domain.entities.UserProfile
import com.babynames.core.presentation.account.AccountsManager
import com.babynames.core.presentation.getApplicationComponent
import com.babynames.core.presentation.managers.PermissionsManager
import com.babynames.login.R
import com.babynames.login.domain.entities.requestObjects.CreateLocalAccountRequestObject
import com.babynames.login.domain.entities.requestObjects.SignInRequestObject
import com.babynames.login.presentation.adapters.WelcomePagerAdapter
import com.babynames.login.presentation.components.SignInComponent
import com.babynames.login.presentation.fragments.WelcomeFragment
import com.babynames.login.presentation.modules.SignInModule
import com.babynames.login.presentation.presenters.abstractions.SignInPresenter
import com.babynames.login.presentation.viewModels.SignInViewModel
import com.facebook.CallbackManager
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_welcome.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.cancelButton
import org.jetbrains.anko.okButton
import java.util.*
import javax.inject.Inject


class WelcomeActivity : AppCompatActivity(), ViewPager.OnPageChangeListener, SignInViewModel {

    private var mAccountAuthenticatorResponse: AccountAuthenticatorResponse? = null
    private var mResultBundle: Bundle? = null

    private var adapterViewpager: FragmentPagerAdapter? = null
    private var dotsCount: Int = 0
    private var dots = arrayOfNulls<ImageView>(4)

    private val callbackManager by lazy {
        CallbackManager.Factory.create()
    }

    private val accountManager by lazy {
        AccountManager.get(this)
    }

    @Inject
    lateinit var signInPresenter: SignInPresenter

    private val signInComponent: SignInComponent by lazy {
        DaggerSignInComponent.builder()
                .applicationComponent(this.getApplicationComponent())
                .signInModule(SignInModule())
                .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        initViews()
        initDotsView()
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == AccountsManager.REQUEST_PERMISSIONS_CODE) {
            if (PermissionsManager.verifyPermissions(grantResults)) {
                val parameters = Bundle()
                parameters.putString("fields", "id, name, email, age_range, birthday, gender, picture.height(300).width(300)")
                this.signInPresenter.getFacebookInfo(parameters)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        for (i in 0 until dotsCount) {
            dots[i]!!.setImageDrawable(getDrawable(R.drawable.welcome_no_selected_item))
        }

        dots[position]!!.setImageDrawable(getDrawable(R.drawable.welcome_selected_item))
    }

    override fun finish() {
        if (this.mAccountAuthenticatorResponse != null) {
            // send the result bundle back if set, otherwise send an error.
            if (this.mResultBundle != null) {
                this.mAccountAuthenticatorResponse?.onResult(this.mResultBundle)
            } else {
                this.mAccountAuthenticatorResponse?.onError(AccountManager.ERROR_CODE_CANCELED,
                        "cancelled")
            }
            this.mAccountAuthenticatorResponse = null
        }
        super.finish()
    }


    override fun displayFacebookInformationRecoveryError() {
        alert {
            message = "Ocurrió un error al intentar acceder a tu información de Facebook"
            okButton {
            }
        }.show()
    }

    override fun checkPermissionsSuccess(boolean: Boolean) {
        val parameters = Bundle()
        parameters.putString("fields", "id, name, email, age_range, birthday, gender, picture.height(300).width(300)")
        this.signInPresenter.getFacebookInfo(parameters)
    }

    override fun requestAccountPermissionRationale(requestCode: Int) {
        alert {
            message = getString(R.string.required_permissions_message)
            okButton {
                this@WelcomeActivity.requestAccountPermissions(requestCode)
            }
            cancelButton {
            }
        }.show()
    }

    override fun requestAccountPermissions(requestCode: Int) {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.GET_ACCOUNTS), requestCode)
    }

    override fun displayErrorAddingAccountErrorMessage() {
        alert {
            message = getString(R.string.error_adding_account_messsage)
            okButton {
            }
        }.show()
    }

    override fun displayExistingAccountErrorMessage() {
        alert {
            message = getString(R.string.only_one_account_per_device)
            okButton {
            }
        }.show()
    }

    override fun displayProfileNullErrorMessage() {
        alert {
            message = "displayProfileNullErrorMessage"
            okButton {
            }
        }.show()
    }

    override fun displayAccountTypeEmptyErrorMessage() {
        alert {
            message = "El account type es necesario"
            okButton {
            }
        }.show()
    }

    override fun onRecoveredFacebookInfoResponse(signInRequestObject: SignInRequestObject) {
        this.signInPresenter.signIn(signInRequestObject)
    }

    override fun onRecoverFacebookInfoError(error: String) {
        alert {
            message = error
            okButton {
            }
        }.show()
    }

    override fun onSignInSuccess(userProfile: UserProfile) {
        val createLocalAccountRequestObject = CreateLocalAccountRequestObject(this.packageName, this.accountManager, userProfile)
        this.signInPresenter.createLocalAccount(createLocalAccountRequestObject)
    }

    override fun onAccountCreated(userData: Bundle) {
        this.mResultBundle = userData
        setResult(AccountsManager.RESULT_CODE_ACCOUNT_CREATED)
        finish()
    }

    override fun displayError(errorMessage: String) {
        alert {
            message = errorMessage
            okButton {
            }
        }.show()
    }

    override fun onSuccess(result: LoginResult?) {
        val parameters = Bundle()
        parameters.putString("fields", "id, name, email, age_range, birthday, gender, picture.height(300).width(300)")
        this.signInPresenter.getFacebookInfo(parameters)
    }

    override fun onCancel() {
    }

    override fun onError(error: FacebookException?) {
        alert {
            message = error?.localizedMessage ?: "Error al intenbtar acceder a Facebook"
            okButton {
            }
        }.show()
    }

    private fun initViews() {
        adapterViewpager = WelcomePagerAdapter(supportFragmentManager, initFragmentList())
        this.vp_welcome.adapter = adapterViewpager
        this.vp_welcome.currentItem = 0
        this.vp_welcome.addOnPageChangeListener(this)

        this.login_button_facebook.setReadPermissions(Arrays.asList("email"))

        this.login_button_facebook.registerCallback(callbackManager, this)
    }

    private fun initDotsView() {

        dotsCount = adapterViewpager!!.count

        for (i in 0 until dotsCount) {
            dots[i] = ImageView(this)
            dots[i]!!.setImageDrawable(getDrawable(R.drawable.welcome_no_selected_item))

            val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            )

            params.setMargins(8, 0, 8, 0)

            this.viewPagerCountDots.addView(dots[i], params)
        }

        dots[0]!!.setImageDrawable(getDrawable(R.drawable.welcome_selected_item))

    }

    private fun initFragmentList(): MutableList<Fragment> {
        val fragmentList = mutableListOf<Fragment>()

        fragmentList.add(WelcomeFragment.newInstance(R.raw.empty_status, getString(R.string.welcome_screen_message), getString(R.string.welcome_screen_description)))
        fragmentList.add(WelcomeFragment.newInstance(R.raw.swipe_left, getString(R.string.decision_screen_message), getString(R.string.decision_screen_description)))
        fragmentList.add(WelcomeFragment.newInstance(R.raw.bookmark_animation, getString(R.string.choose_screen_message), getString(R.string.choose_screen_description)))
        fragmentList.add(WelcomeFragment.newInstance(R.raw.account_success, getString(R.string.finish_screen_message), getString(R.string.finish_screen_description)))

        return fragmentList
    }
}
