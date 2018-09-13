package com.babynames.profile.presentation.presenters.abstractions

import com.babynames.core.presentation.presenters.Presenter
import com.babynames.profile.domain.entities.requestObjects.GetCoupleRequestObject
import com.babynames.profile.domain.entities.requestObjects.SetCoupleRequestObject
import com.babynames.profile.presentation.viewModels.ScanCodeViewModel

interface CouplePresenter : Presenter<ScanCodeViewModel> {
    fun setCouple(setCoupleRequestObject: SetCoupleRequestObject)

    fun getCouple(getCoupleRequestObject: GetCoupleRequestObject)
}