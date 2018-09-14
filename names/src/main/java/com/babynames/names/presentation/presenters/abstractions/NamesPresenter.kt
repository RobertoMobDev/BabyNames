package com.babynames.names.presentation.presenters.abstractions

import com.babynames.core.presentation.presenters.Presenter
import com.babynames.database.FavoritesEntity
import com.babynames.names.presentation.viewModels.NamesViewModel

interface NamesPresenter : Presenter<NamesViewModel> {

    fun getNamesList(type: String)

    fun saveNameIntoFavorites(nameToFavorite: FavoritesEntity)

}