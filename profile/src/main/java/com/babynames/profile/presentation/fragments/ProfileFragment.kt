package com.babynames.profile.presentation.fragments


import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.babynames.gender.presentation.GenderActivity
import com.babynames.login.presentation.WelcomeActivity
import com.babynames.profile.R
import com.babynames.profile.presentation.activities.AboutActivity
import com.babynames.profile.presentation.activities.ScanQRActivity
import com.facebook.login.LoginManager
import kotlinx.android.synthetic.main.fragment_profile.*
import org.jetbrains.anko.cancelButton
import org.jetbrains.anko.okButton
import org.jetbrains.anko.support.v4.alert

class ProfileFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }

    private val dialogBuilder: AlertDialog.Builder by lazy {
        AlertDialog.Builder(activity!!, R.style.AlertDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        val items = arrayOf(getString(R.string.profile_dialog_first_option),
                getString(R.string.profile_dialog_second_option))

        dialogBuilder
                .setTitle(context!!.resources.getString(R.string.profile_dialog_title))
                .setItems(items) { _, p1 ->
                    when (p1) {
                        0 -> {
                            startActivity(Intent(context, ScanQRActivity::class.java))
                        }
                        1 -> {

                        }
                    }
                }
        dialogBuilder.create()
        dialogBuilder.show()
    }

    private fun logOut() {
        alert {
            title = getString(R.string.profile_logout_dialog_title)
            message = getString(R.string.profile_logout_dialog_message)
            cancelButton { }
            okButton {
                LoginManager.getInstance().logOut()
                startActivity(Intent(context, WelcomeActivity::class.java))
                activity?.finish()
            }
        }.show()
    }


}
