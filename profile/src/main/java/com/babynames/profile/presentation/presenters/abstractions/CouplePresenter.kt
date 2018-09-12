package com.babynames.profile.presentation.presenters.abstractions

import com.babynames.core.presentation.presenters.Presenter
import com.babynames.profile.presentation.viewModels.ScanCodeViewModel

interface CouplePresenter : Presenter<ScanCodeViewModel> {
    fun setCouple(type: String, user1: String, user2: String)
}