package com.babynames.babynames.core.presentation.presenters

import com.babynames.babynames.core.presentation.viewModels.BaseView

interface Presenter<in T : BaseView> {

    fun bind(view: T)

    fun onDestroy()

}