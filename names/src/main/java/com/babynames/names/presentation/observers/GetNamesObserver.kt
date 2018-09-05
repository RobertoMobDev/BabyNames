package com.babynames.names.presentation.observers

import com.babynames.core.presentation.observers.DefaultObserver
import com.babynames.names.R
import com.babynames.names.domain.entities.responseObjects.NameResponseObject
import com.babynames.names.presentation.viewModels.NamesViewModel

class GetNamesObserver(view: NamesViewModel?) : DefaultObserver<List<NameResponseObject>, NamesViewModel>(view) {

    override fun onComplete() {

    }

    override fun onNext(t: List<NameResponseObject>) {
        this.view?.onGetNamesSuccess(t)
    }

    override fun onError(e: Throwable) {
        this.view?.displayError(R.string.get_names_error)
    }
}