package com.babynames.babynames.presentation.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.babynames.babynames.R
import kotlinx.android.synthetic.main.fragment_names.*

class NamesFragment : Fragment() {

    companion object {
        fun newInstance(): NamesFragment {
            return NamesFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_names, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.animation_empty_names.setAnimation(R.raw.empty_status)
        this.animation_empty_names.playAnimation()
    }


}
