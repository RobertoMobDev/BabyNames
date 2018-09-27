package com.babynames.babynames

import android.os.Bundle
import android.support.v4.app.Fragment
import com.babynames.babynames.presentation.adapters.HomePagerAdapter
import com.babynames.babynames.presentation.fragments.FavoritesFragment
import com.babynames.babynames.presentation.fragments.MatchesFragment
import com.babynames.core.domain.entities.UserProfile
import com.babynames.core.presentation.activities.BaseAuthenticationActivity
import com.babynames.names.presentation.fragments.NamesFragment
import com.babynames.profile.presentation.fragments.ProfileFragment
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseAuthenticationActivity() {

    private val fragmentList: List<Fragment> by lazy {
        listOf(NamesFragment.newInstance(), MatchesFragment.newInstance(), FavoritesFragment.newInstance(), ProfileFragment.newInstance(this.userProfile))
    }

    private val firebaseAnalytics: FirebaseAnalytics by lazy {
        FirebaseAnalytics.getInstance(this);
    }


    private val homePagerAdapter: HomePagerAdapter by lazy {
        HomePagerAdapter(this.supportFragmentManager, this.fragmentList)
    }

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun initView() {

        this.bottom_nav_view.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_names -> {
                    this.main_activity_fragment_container.currentItem = 0
                    recordItemSelection("names")
                }

                R.id.action_matches -> {
                    this.main_activity_fragment_container.currentItem = 1
                    recordItemSelection("matches")
                }

                R.id.action_favorites -> {
                    this.main_activity_fragment_container.currentItem = 2
                    recordItemSelection("favorites")
                }

                R.id.action_profile -> {
                    this.main_activity_fragment_container.currentItem = 3
                    recordItemSelection("profile")
                }

            }

            true
        }
    }

    override fun getUserProfile(userProfile: UserProfile) {
        this.main_activity_fragment_container.adapter = homePagerAdapter
    }

    private fun recordItemSelection(item: String) {
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, R.id.action_profile.toString())
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, item)
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "fragment")
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
    }
}
