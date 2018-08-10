package com.babynames.core.presentation.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem

/**
 * Created by rsandoval on 27/02/2018.
 */
abstract class BaseActivity : AppCompatActivity() {

    abstract fun getLayoutResId(): Int

    abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (getLayoutResId() != 0) {
            setContentView(getLayoutResId())
        }
        initView()

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                this.onBackPressed()
                true
            }
            else -> false
        }
    }

    class Transitions(val onCreateEnterAnimation: Int,
                      val onCreateExitAnimation: Int,
                      val onBackPressedEnterAnimation: Int,
                      val onBackPressedExitAnimation: Int)
}