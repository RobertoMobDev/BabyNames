package com.babynames.names.presentation.viewModels

import com.babynames.core.presentation.viewModels.SingleProgressView
import com.babynames.names.domain.entities.Name
import com.babynames.names.domain.entities.responseObjects.NameResponseObject

interface NamesViewModel : SingleProgressView {

    fun onGetNamesSuccess(namesList: List<Name>)

}