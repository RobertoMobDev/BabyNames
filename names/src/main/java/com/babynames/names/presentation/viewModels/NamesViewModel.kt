package com.babynames.names.presentation.viewModels

import com.babynames.core.presentation.viewModels.BaseView
import com.babynames.names.domain.entities.responseObjects.NameResponseObject

interface NamesViewModel : BaseView {

    fun onGetNamesSuccess(namesList: List<NameResponseObject>)

}