package com.babynames.names.data.repositoryimplementations

import com.babynames.database.FavoritesEntity
import com.babynames.names.data.datasourceimplementations.NamesApiDataSourceImpl
import com.babynames.names.domain.entities.Name
import com.babynames.names.domain.entities.responseObjects.NameResponseObject
import com.babynames.names.domain.repositoryAbstractions.NamesRepository
import io.reactivex.Observable
import javax.inject.Inject

class NamesRepositoryImpl @Inject constructor(private val namesDataSourceImpl: NamesApiDataSourceImpl) : NamesRepository {

    override fun getNames(type: String): Observable<List<Name>> = this.namesDataSourceImpl.getNames(type)

//    override fun inserNameToFavorite(nameToFavoritesEntity: FavoritesEntity): Observable<Boolean> {
//
//    }
}