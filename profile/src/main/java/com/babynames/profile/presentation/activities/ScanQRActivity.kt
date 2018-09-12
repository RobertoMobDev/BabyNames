package com.babynames.profile.presentation.activities

import android.Manifest
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.transition.TransitionManager
import android.view.View
import com.babynames.profile.R
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.ia.mchaveza.kotlin_library.PermissionCallback
import com.ia.mchaveza.kotlin_library.PermissionManager
import kotlinx.android.synthetic.main.activity_scan_qr.*
import org.jetbrains.anko.*

class ScanQRActivity : AppCompatActivity(), PermissionCallback {

    private val codeScanner: CodeScanner by lazy {
        CodeScanner(this, this.scanner_view)
    }

    private val permissionManager: PermissionManager by lazy {
        PermissionManager(this, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_qr)

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

    private fun decodeQR() {
        val visible = true
        TransitionManager.beginDelayedTransition(this.layout_scan)
        this.animation_scan_qr.visibility = if (visible) View.INVISIBLE else View.VISIBLE
        this.text_scan_qr_tutorial.visibility = if (visible) View.INVISIBLE else View.VISIBLE
        this.button_scan_qr.visibility = if (visible) View.INVISIBLE else View.VISIBLE
        scanner_view.visibility = if (visible) View.VISIBLE else View.GONE
        this.codeScanner.decodeCallback = DecodeCallback {
            //Servicio para guardar pareja
            finish()
        }
    }
}
