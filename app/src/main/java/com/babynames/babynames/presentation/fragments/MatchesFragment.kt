package com.babynames.babynames.presentation.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.babynames.babynames.R
import kotlinx.android.synthetic.main.fragment_matches.*

class MatchesFragment : Fragment() {

    companion object {
        fun newInstance(): MatchesFragment {
            return MatchesFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_matches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.partner_animation_view.setAnimation(R.raw.drink)
        this.animation_matches_empty.setAnimation(R.raw.emoji_wink)
    }


}
