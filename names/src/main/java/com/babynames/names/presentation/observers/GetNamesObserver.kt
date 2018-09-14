package com.babynames.names.presentation.observers

import android.view.View
import com.babynames.core.presentation.observers.DefaultObserver
import com.babynames.names.R
import com.babynames.names.domain.entities.Name
import com.babynames.names.domain.entities.responseObjects.NameResponseObject
import com.babynames.names.presentation.viewModels.NamesViewModel

class GetNamesObserver(view: NamesViewModel?) : DefaultObserver<List<Name>, NamesViewModel>(view) {

    override fun onComplete() {

    }

    override fun onStart() {
        super.onStart()
        this.view?.setProgressVisibility(View.VISIBLE)
    }

    override fun onNext(t: List<Name>) {
        this.view?.setProgressVisibility(View.INVISIBLE)
        this.view?.onGetNamesSuccess(t)
    }

    override fun onError(e: Throwable) {
        this.view?.setProgressVisibility(View.INVISIBLE)
        this.view?.displayError(R.string.get_names_error)
    }
}