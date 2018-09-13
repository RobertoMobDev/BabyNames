package com.babynames.profile.presentation.observers

import com.babynames.core.presentation.observers.DefaultObserver
import com.babynames.profile.R
import com.babynames.profile.domain.entities.responseObjects.GetCoupleResponseObject
import com.babynames.profile.presentation.viewModels.ScanCodeViewModel

class GetCoupleObserver(view: ScanCodeViewModel?) : DefaultObserver<GetCoupleResponseObject, ScanCodeViewModel>(view) {

    override fun onComplete() {}

    override fun onNext(t: GetCoupleResponseObject) {
        this.view?.onGetPartnerSuccess(t)
    }

    override fun onError(e: Throwable) {
        this.view?.displayError(R.string.get_couple_error)
    }
}