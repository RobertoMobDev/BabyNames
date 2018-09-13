package com.babynames.profile.presentation.observers

import com.babynames.core.presentation.observers.DefaultObserver
import com.babynames.profile.R
import com.babynames.profile.domain.entities.responseObjects.CoupleResponseObject
import com.babynames.profile.presentation.viewModels.ScanCodeViewModel

class SetCoupleObserver(view: ScanCodeViewModel?) : DefaultObserver<CoupleResponseObject, ScanCodeViewModel>(view) {

    override fun onComplete() {}

    override fun onNext(t: CoupleResponseObject) {
        this.view?.onSetPartnerSuccess(t)
    }

    override fun onError(e: Throwable) {
        this.view?.displayError(R.string.set_couple_error)
    }
}