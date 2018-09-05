package com.babynames.core.presentation.viewModels

import android.support.annotation.StringRes

interface BaseView {

    fun displayError(errorMessage: String)

    fun displayError(@StringRes errorMessage: Int)

}