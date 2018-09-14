package com.babynames.profile.presentation.fragments


import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.babynames.core.domain.entities.UserProfile
import com.babynames.core.presentation.getApplicationComponent
import com.babynames.gender.presentation.GenderActivity
import com.babynames.login.domain.entities.responseObjects.UserProfileResponse
import com.babynames.login.presentation.WelcomeActivity
import com.babynames.profile.R
import com.babynames.profile.presentation.activities.AboutActivity
import com.babynames.profile.presentation.activities.MyCodeActivity
import com.babynames.profile.presentation.activities.ScanQRActivity
import com.babynames.profile.presentation.components.CoupleComponent
import com.babynames.profile.presentation.components.DaggerCoupleComponent
import com.babynames.profile.presentation.modules.CoupleModule
import com.babynames.profile.presentation.views.CircleTransformation
import com.facebook.login.LoginManager
import com.ia.mchaveza.kotlin_library.SharedPreferencesManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.alert
import javax.inject.Inject

@Suppress("DEPRECATION")
class ProfileFragment : Fragment(), View.OnClickListener {

    @Inject
    lateinit var sharedPreferencesManager: SharedPreferencesManager

    private lateinit var userId: String
    private lateinit var setPartnerDialog: DialogInterface

    private val SCAN_QR_REQUEST = 3000

    private val coupleComponent: CoupleComponent by lazy {
        DaggerCoupleComponent.builder()
                .applicationComponent(activity?.getApplicationComponent())
                .coupleModule(CoupleModule())
                .build()
    }

    companion object {
        fun newInstance(userProfile: UserProfile?): ProfileFragment {
            return ProfileFragment().apply {
                this.arguments = Bundle().apply {
                    this.putParcelable("user", userProfile)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.coupleComponent.inject(this)

        val userProfile: UserProfile

        if (arguments != null) {
            userProfile = arguments?.getParcelable("user")!!

            userId = userProfile.id

            this.text_profile_name.text = userProfile.name
            Picasso.get().load(userProfile.profileImage).transform(CircleTransformation()).into(this.image_user_picture)

        } else {
            userId = ""
        }

        if (!sharedPreferencesManager.getSharedPreference("coupleName", "").isBlank() &&
                !sharedPreferencesManager.getSharedPreference("couplePicture", "").isBlank()) {

            this.text_add_partner.visibility = View.GONE
            this.couple_profile_layout_section.visibility = View.VISIBLE
            this.text_couple_name.text = sharedPreferencesManager.getSharedPreference("coupleName", "")
            Picasso.get().load(sharedPreferencesManager.getSharedPreference("couplePicture", "")).into(this.image_couple_picture)
        }



        this.profile_layout_section.setOnClickListener(this)
        this.share_layout_section.setOnClickListener(this)
        this.feedback_layout_section.setOnClickListener(this)
        this.gender_layout_section.setOnClickListener(this)
        this.about_layout_section.setOnClickListener(this)
        this.partner_layout_section.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {

            R.id.profile_layout_section -> logOut()

            R.id.share_layout_section -> shareApp()

            R.id.feedback_layout_section -> feedBackApp()

            R.id.gender_layout_section -> changeGender()

            R.id.about_layout_section -> aboutUs()

            R.id.partner_layout_section -> setPartner()

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SCAN_QR_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                this.text_add_partner.visibility = View.GONE
                this.couple_profile_layout_section.visibility = View.VISIBLE
                this.text_couple_name.text = data?.getParcelableExtra<UserProfileResponse>("couple")?.name
                Picasso.get().load(data?.getParcelableExtra<UserProfileResponse>("couple")?.profileImage).transform(CircleTransformation()).into(this.image_couple_picture)
            }
        }
    }

    private fun shareApp() {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_VIEW
            addCategory(Intent.CATEGORY_BROWSABLE)
            data = Uri.parse("http://babynamesapps.com")
        }

        startActivity(shareIntent)
    }

    private fun feedBackApp() {
        val sendEmailIntent = Intent().apply {
            action = Intent.ACTION_SENDTO
            type = "*/*"
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf("roberto.mob.dev@gmail.com"))
            putExtra(Intent.EXTRA_SUBJECT, context!!.resources.getString(R.string.profile_feedback_email_subject))
        }

        try {
            startActivity(Intent.createChooser(sendEmailIntent, context!!.resources.getString(R.string.profile_feedback_chooser_header)))
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context!!, context!!.resources.getString(R.string.profile_feedback_failed_share_message), Toast.LENGTH_LONG).show()
        }
    }

    private fun changeGender() {
        val intent = Intent(context, GenderActivity::class.java)
        intent.putExtra("isGender", true)
        startActivity(intent)
    }

    private fun aboutUs() {
        startActivity(Intent(context, AboutActivity::class.java))
    }

    private fun setPartner() {
        setPartnerDialog = alert {
            customView {
                verticalLayout {
                    textView(getString(R.string.profile_dialog_title)) {
                        textColor = resources.getColor(R.color.colorPrimaryDark)
                        textSize = 18f
                        setPadding(36, 36, 16, 8)
                    }

                    textView(getString(R.string.profile_dialog_first_option)) {
                        textColor = resources.getColor(R.color.colorBlack)
                        textSize = 16f
                        setPadding(36, 36, 16, 8)
                    }
                            .setOnClickListener {
                                val intent = Intent(context, ScanQRActivity::class.java)
                                intent.putExtra("userId", userId)
                                startActivityForResult(intent, SCAN_QR_REQUEST)
                                setPartnerDialog.dismiss()
                            }

                    textView(getString(R.string.profile_dialog_second_option)) {
                        textColor = resources.getColor(R.color.colorBlack)
                        textSize = 16f
                        setPadding(36, 36, 16, 8)
                    }.lparams { bottomMargin = dip(20) }
                            .setOnClickListener {
                                val intent = Intent(context, MyCodeActivity::class.java)
                                intent.putExtra("userId", userId)
                                startActivity(intent)
                                setPartnerDialog.dismiss()
                            }
                }
            }
        }.show()
    }

    private fun logOut() {
        alert {
            customView {
                verticalLayout {
                    textView(getString(R.string.profile_logout_dialog_title)) {
                        textColor = resources.getColor(R.color.colorPrimaryDark)
                        textSize = 18f
                        setPadding(36, 36, 16, 8)
                    }

                    textView(getString(R.string.profile_logout_dialog_message)) {
                        textColor = resources.getColor(R.color.colorBlack)
                        textSize = 16f
                        setPadding(36, 36, 16, 8)
                    }
                }
            }

            cancelButton { }
            okButton {
                LoginManager.getInstance().logOut()
                startActivity(Intent(context, WelcomeActivity::class.java))
                activity?.finish()
            }
        }.show()
    }


}
