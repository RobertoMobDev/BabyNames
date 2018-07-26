package com.babynames.babynames.names.presentation

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import com.babynames.babynames.R
import com.babynames.babynames.names.presentation.adapters.WelcomePagerAdapter
import com.babynames.babynames.names.presentation.fragments.WelcomeFragment
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_welcome.*
import java.util.*


class WelcomeActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {

    private var adapterViewpager: FragmentPagerAdapter? = null
    private var dotsCount: Int = 0
    private var dots = arrayOfNulls<ImageView>(4)

    private val callbackManager by lazy {
        CallbackManager.Factory.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        initViews()
        initDotsView()
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

    private fun initViews() {
        adapterViewpager = WelcomePagerAdapter(supportFragmentManager, initFragmentList())
        this.vp_welcome.adapter = adapterViewpager
        this.vp_welcome.currentItem = 0
        this.vp_welcome.addOnPageChangeListener(this)

        this.login_button_facebook.setReadPermissions(Arrays.asList("email"))

        this.login_button_facebook.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                val graphRequest = GraphRequest.newMeRequest(result?.accessToken) { graphResponse, response ->
                    if (response.error != null) {

                    } else {
                        val email = graphResponse?.getString("email")
                        val name = graphResponse?.getString("name")
                    }
                }

                val parameters = Bundle()
                parameters.putString("fields", "id, name, email, gender, birthday")
                graphRequest.parameters = parameters
                graphRequest.executeAsync()
            }

            override fun onCancel() {

            }

            override fun onError(error: FacebookException) {
                Log.e("ERROR FACE", error.message)
            }

        })
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
