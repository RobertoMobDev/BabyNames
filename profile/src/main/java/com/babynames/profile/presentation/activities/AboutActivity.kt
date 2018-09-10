package com.babynames.profile.presentation.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.babynames.profile.R
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        this.animation_ios.setAnimation(R.raw.iphone_x_loading)
        this.animation_android.setAnimation(R.raw.android)
        this.animation_web.setAnimation(R.raw.animated_laptop)

        this.animation_ios.playAnimation()
        this.animation_android.playAnimation()
        this.animation_web.playAnimation()

        supportActionBar?.title = getString(R.string.profile_about_text)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> this.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
