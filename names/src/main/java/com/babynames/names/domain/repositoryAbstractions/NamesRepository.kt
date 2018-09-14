package com.babynames.names.domain.repositoryAbstractions

import com.babynames.database.FavoritesEntity
import com.babynames.names.domain.entities.Name
import com.babynames.names.domain.entities.responseObjects.NameResponseObject
import io.reactivex.Observable

interface NamesRepository {
    fun getNames(type: String): Observable<List<Name>>

    //fun insertNameToFavorite(nameToFavoritesEntity: FavoritesEntity): Observable<Boolean>
}