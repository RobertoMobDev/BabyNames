package com.babynames.babynames.names.presentation.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.babynames.babynames.R
import kotlinx.android.synthetic.main.fragment_welcome.*

class WelcomeFragment : Fragment() {

    companion object {
        fun newInstance(lottieAnimationName: Int, welcomeMessage: String, welcomeDescription: String): WelcomeFragment {
            val welcomeFragment = WelcomeFragment()
            val bundle = Bundle()
            bundle.putInt("animation", lottieAnimationName)
            bundle.putString("message", welcomeMessage)
            bundle.putString("description", welcomeDescription)

            welcomeFragment.arguments = bundle

            return welcomeFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!arguments!!.isEmpty) {
            this.animation_welcome.setAnimation(arguments!!.getInt("animation"))
            this.animation_welcome.playAnimation()
            this.text_welcome_message.text = arguments!!.getString("message")
            this.text_welcome_description.text = arguments!!.getString("description")
        }
    }


}
