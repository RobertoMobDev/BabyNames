package com.babynames.profile.presentation.activities

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Point
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.view.Display
import android.view.View
import com.babynames.profile.R
import com.ia.mchaveza.kotlin_library.PermissionCallback
import com.ia.mchaveza.kotlin_library.PermissionManager
import kotlinx.android.synthetic.main.activity_my_code.*
import net.glxn.qrgen.android.QRCode
import org.jetbrains.anko.*

class MyCodeActivity : AppCompatActivity(), View.OnClickListener, PermissionCallback {

    private val permissionManager: PermissionManager by lazy {
        PermissionManager(this, this)
    }

    private lateinit var bitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_code)

        if (intent.getStringExtra("userId") != null && intent.getStringExtra("userId").isNotBlank())
            generateQRCode(intent.getStringExtra("userId") )

        this.button_share_qr.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.button_share_qr -> {
                if (this.permissionManager.permissionGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    shareQRCode()
                } else {
                    this.permissionManager.requestSinglePermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                }
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

                    textView(context.getString(R.string.qr_permission_no_granted_message)) {
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
        shareQRCode()
    }

    private fun generateQRCode(myCode: String) {
        val screenWidth = getScreenDimensions()[0]
        val screenHeight = getScreenDimensions()[1]

        bitmap = if (screenWidth >= 1080 && screenHeight >= 1794) {
            QRCode.from(myCode).withSize(900, 900).bitmap()
        } else {
            QRCode.from(myCode).withSize(300, 300).bitmap()
        }

        this.image_qr_code.imageBitmap = bitmap
    }

    private fun shareQRCode() {

        val bitmapPath = MediaStore.Images.Media.insertImage(contentResolver, bitmap, "babyNamesQR", null)
        val bitmapUri = Uri.parse(bitmapPath)
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "image/png"
        intent.putExtra(Intent.EXTRA_STREAM, bitmapUri)
        startActivity(Intent.createChooser(intent, getString(R.string.qr_share_text)))

    }

    private fun getScreenDimensions(): ArrayList<Int> {
        val display: Display = windowManager.defaultDisplay
        val size = Point()
        val dimensions = ArrayList<Int>()
        val width: Int
        val height: Int
        display.getSize(size)
        width = size.x
        height = size.y
        dimensions.add(width)
        dimensions.add(height)

        return dimensions
    }
}
