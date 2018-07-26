package com.babynames.babynames.names.presentation.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class WelcomePagerAdapter(fragmentManager: FragmentManager, private val fragmentList: MutableList<Fragment>) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return this.fragmentList[position]
    }

    override fun getCount(): Int = this.fragmentList.size
}