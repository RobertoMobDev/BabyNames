package com.babynames.babynames

import com.babynames.core.domain.entities.UserProfile
import com.babynames.core.presentation.activities.BaseAuthenticationActivity

class MainActivity : BaseAuthenticationActivity() {

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun initView() {

    }

    override fun getUserProfile(userProfile: UserProfile) {

    }
}
