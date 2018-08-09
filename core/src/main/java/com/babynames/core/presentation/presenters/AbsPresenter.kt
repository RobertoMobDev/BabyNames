package com.babynames.core.presentation.presenters

import com.babynames.core.presentation.viewModels.BaseView

abstract class AbsPresenter<T : BaseView> : Presenter<T> {

    private var _view: T? = null

    val view: T?
        get() = this._view

    abstract fun onBind(view: T)

    override fun bind(view: T) {
        this._view = view
        this.onBind(this._view!!)
    }

    override fun onDestroy() {
        this._view = null
    }
}