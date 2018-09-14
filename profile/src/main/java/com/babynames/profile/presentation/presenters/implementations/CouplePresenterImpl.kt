package com.babynames.profile.presentation.presenters.implementations

import com.babynames.core.presentation.presenters.AbsPresenter
import com.babynames.profile.domain.entities.requestObjects.GetCoupleRequestObject
import com.babynames.profile.domain.entities.requestObjects.SetCoupleRequestObject
import com.babynames.profile.domain.usecases.GetCoupleUseCase
import com.babynames.profile.domain.usecases.SetCoupleUseCase
import com.babynames.profile.presentation.observers.GetCoupleObserver
import com.babynames.profile.presentation.observers.SetCoupleObserver
import com.babynames.profile.presentation.presenters.abstractions.CouplePresenter
import com.babynames.profile.presentation.viewModels.ScanCodeViewModel
import javax.inject.Inject

class CouplePresenterImpl @Inject constructor(private val setCoupleUseCase: SetCoupleUseCase,
                                              private val getCoupleUseCase: GetCoupleUseCase) : AbsPresenter<ScanCodeViewModel>(), CouplePresenter {

    override fun onBind(view: ScanCodeViewModel) {}

    override fun onDestroy() {
        super.onDestroy()
        this.setCoupleUseCase.dispose()
        this.getCoupleUseCase.dispose()
    }

    override fun setCouple(setCoupleRequestObject: SetCoupleRequestObject) {
        this.setCoupleUseCase.execute(SetCoupleObserver(this.view), setCoupleRequestObject)
    }

    override fun getCouple(getCoupleRequestObject: GetCoupleRequestObject) {
        this.getCoupleUseCase.execute(GetCoupleObserver(this.view), getCoupleRequestObject)
    }
}