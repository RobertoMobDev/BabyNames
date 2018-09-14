package com.babynames.profile.presentation.activities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.transition.TransitionManager
import android.view.View
import com.babynames.core.presentation.getApplicationComponent
import com.babynames.profile.R
import com.babynames.profile.domain.entities.requestObjects.GetCoupleRequestObject
import com.babynames.profile.domain.entities.requestObjects.SetCoupleRequestObject
import com.babynames.profile.domain.entities.responseObjects.CoupleResponseObject
import com.babynames.profile.domain.entities.responseObjects.GetCoupleResponseObject
import com.babynames.profile.presentation.components.CoupleComponent
import com.babynames.profile.presentation.components.DaggerCoupleComponent
import com.babynames.profile.presentation.modules.CoupleModule
import com.babynames.profile.presentation.presenters.abstractions.CouplePresenter
import com.babynames.profile.presentation.viewModels.ScanCodeViewModel
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.ia.mchaveza.kotlin_library.PermissionCallback
import com.ia.mchaveza.kotlin_library.PermissionManager
import com.ia.mchaveza.kotlin_library.SharedPreferencesManager
import kotlinx.android.synthetic.main.activity_scan_qr.*
import org.jetbrains.anko.*
import javax.inject.Inject

@Suppress("DEPRECATION")
class ScanQRActivity : AppCompatActivity(), PermissionCallback, ScanCodeViewModel {

    @Inject
    lateinit var sharedPreferencesManager: SharedPreferencesManager

    @Inject
    lateinit var couplePresenter: CouplePresenter

    private lateinit var userId: String

    private val coupleComponent: CoupleComponent by lazy {
        DaggerCoupleComponent.builder()
                .applicationComponent(getApplicationComponent())
                .coupleModule(CoupleModule())
                .build()
    }

    private val codeScanner: CodeScanner by lazy {
        CodeScanner(this, this.scanner_view)
    }

    private val permissionManager: PermissionManager by lazy {
        PermissionManager(this, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_qr)

        this.coupleComponent.inject(this)

        userId = if (intent.getStringExtra("userId") != null) {
            intent.getStringExtra("userId")
        } else {
            ""
        }

        this.codeScanner.startPreview()
        this.animation_scan_qr.setAnimation(R.raw.scan_qr_code_success)
        this.animation_scan_qr.playAnimation()

        this.button_scan_qr.setOnClickListener {

            if (permissionManager.permissionGranted(Manifest.permission.CAMERA)) {
                decodeQR()
            } else {
                permissionManager.requestSinglePermission(Manifest.permission.CAMERA)
            }

        }

    }

    override fun onStart() {
        super.onStart()
        this.couplePresenter.bind(this)
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    override fun finish() {
        super.finish()
        this.couplePresenter.onDestroy()
    }

    override fun onPermissionDenied(permission: String) {
        alert {
            customView {
                verticalLayout {

                    textView(context.getString(R.string.qr_permission_no_granted_title)) {
                        textColor = resources.getColor(R.color.colorPrimaryDark)
                        textSize = 18f
                        setPadding(36, 36, 36, 8)
                    }

                    textView(context.getString(R.string.scan_permission_no_granted_message)) {
                        textColor = resources.getColor(R.color.colorBlack)
                        textSize = 16f
                        setPadding(36, 36, 36, 8)
                    }
                }
            }

            okButton { }
        }.show()
    }

    override fun onPermissionGranted(permission: String) {
        decodeQR()
    }

    override fun onSetPartnerSuccess(coupleResponseObject: CoupleResponseObject) {
        this.couplePresenter.getCouple(GetCoupleRequestObject("GetCouple", coupleResponseObject.id))
    }

    override fun onGetPartnerSuccess(getCoupleResponseObject: GetCoupleResponseObject) {
        val resultIntent = Intent()

        sharedPreferencesManager.setSharedPreference("coupleName", getCoupleResponseObject.data[0].name)
        sharedPreferencesManager.setSharedPreference("couplePicture", getCoupleResponseObject.data[0].profileImage)

        alert {
            customView {
                verticalLayout {
                    textView(context.getString(R.string.scan_qr_success_title)) {
                        textColor = resources.getColor(R.color.colorPrimaryDark)
                        textSize = 18f
                        setPadding(36, 36, 16, 8)
                    }

                    textView(context.getString(R.string.scan_qr_success_message)) {
                        textColor = resources.getColor(R.color.colorBlack)
                        textSize = 16f
                        setPadding(36, 36, 16, 8)
                    }
                }
            }

            okButton {
                resultIntent.putExtra("couple", getCoupleResponseObject.data[0])
                setResult(Activity.RESULT_OK, intent)
                finish()
            }

            onCancelled {
                resultIntent.putExtra("couple", getCoupleResponseObject.data[0])
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }.show()

    }

    override fun displayError(errorMessage: String) {

    }

    override fun displayError(errorMessage: Int) {
        alert {
            customView {
                verticalLayout {
                    textView(context.getString(R.string.scan_qr_error_title)) {
                        textColor = resources.getColor(R.color.colorPrimaryDark)
                        textSize = 18f
                        setPadding(36, 36, 16, 8)
                    }

                    textView(getString(errorMessage)) {
                        textColor = resources.getColor(R.color.colorBlack)
                        textSize = 16f
                        setPadding(36, 36, 16, 8)
                    }
                }
            }

            okButton {
                this@ScanQRActivity.onBackPressed()
            }
        }.show()
    }

    private fun decodeQR() {
        val visible = true
        TransitionManager.beginDelayedTransition(this.layout_scan)
        this.animation_scan_qr.visibility = if (visible) View.INVISIBLE else View.VISIBLE
        this.text_scan_qr_tutorial.visibility = if (visible) View.INVISIBLE else View.VISIBLE
        this.button_scan_qr.visibility = if (visible) View.INVISIBLE else View.VISIBLE
        scanner_view.visibility = if (visible) View.VISIBLE else View.GONE
        this.codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                codeScanner.stopPreview()
                this.couplePresenter.setCouple(SetCoupleRequestObject("SetCouple", userId, it.text))
            }
        }
    }
}
