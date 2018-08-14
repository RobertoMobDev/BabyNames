package com.babynames.babynames

import android.support.v4.app.Fragment
import com.babynames.babynames.presentation.adapters.HomePagerAdapter
import com.babynames.babynames.presentation.fragments.FavoritesFragment
import com.babynames.babynames.presentation.fragments.MatchesFragment
import com.babynames.babynames.presentation.fragments.NamesFragment
import com.babynames.babynames.presentation.fragments.ProfileFragment
import com.babynames.core.domain.entities.UserProfile
import com.babynames.core.presentation.activities.BaseAuthenticationActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseAuthenticationActivity() {

    private val fragmentList: List<Fragment> by lazy {
        listOf(NamesFragment.newInstance(), MatchesFragment.newInstance(), FavoritesFragment.newInstance(), ProfileFragment.newInstance())
    }

    private val homePagerAdapter: HomePagerAdapter by lazy {
        HomePagerAdapter(this.supportFragmentManager, this.fragmentList)
    }

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun initView() {
        this.main_activity_fragment_container.adapter = homePagerAdapter

        this.bottom_nav_view.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_names -> this.main_activity_fragment_container.currentItem = 0
                R.id.action_matches -> this.main_activity_fragment_container.currentItem = 1
                R.id.action_favorites -> this.main_activity_fragment_container.currentItem = 2
                R.id.action_profile -> this.main_activity_fragment_container.currentItem = 3

            }

            true
        }

    }

    override fun getUserProfile(userProfile: UserProfile) {

    }
}
