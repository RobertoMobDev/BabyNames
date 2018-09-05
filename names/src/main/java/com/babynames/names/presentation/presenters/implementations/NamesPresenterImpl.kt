package com.babynames.names.presentation.presenters.implementations

import com.babynames.core.presentation.presenters.AbsPresenter
import com.babynames.names.domain.useCases.GetNamesUseCase
import com.babynames.names.presentation.observers.GetNamesObserver
import com.babynames.names.presentation.presenters.abstractions.NamesPresenter
import com.babynames.names.presentation.viewModels.NamesViewModel
import javax.inject.Inject

class NamesPresenterImpl @Inject constructor(private val getNamesUseCase: GetNamesUseCase) : AbsPresenter<NamesViewModel>(), NamesPresenter {

    override fun onBind(view: NamesViewModel) {

    }

    override fun onDestroy() {
        super.onDestroy()
        this.getNamesUseCase.dispose()
    }

    override fun getNamesList(type: String) {
        this.getNamesUseCase.execute(GetNamesObserver(this.view), type)
    }
}