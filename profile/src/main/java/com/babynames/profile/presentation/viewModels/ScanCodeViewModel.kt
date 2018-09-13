package com.babynames.profile.presentation.viewModels

import com.babynames.core.presentation.viewModels.BaseView
import com.babynames.profile.domain.entities.responseObjects.CoupleResponseObject
import com.babynames.profile.domain.entities.responseObjects.GetCoupleResponseObject

interface ScanCodeViewModel : BaseView {
    fun onSetPartnerSuccess(coupleResponseObject: CoupleResponseObject)
    fun onGetPartnerSuccess(getCoupleResponseObject: GetCoupleResponseObject)
}