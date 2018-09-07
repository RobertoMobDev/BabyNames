package com.babynames.profile.presentation.fragments


import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.babynames.login.presentation.WelcomeActivity
import com.babynames.profile.R
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.profile_layout_section.setOnClickListener(this)
        this.share_layout_section.setOnClickListener(this)
        this.feedback_layout_section.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.profile_layout_section -> logOut()

            R.id.share_layout_section -> shareApp()

            R.id.feedback_layout_section -> feedBackApp()

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
