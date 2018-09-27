package com.babynames.names.data.repositoryimplementations

import com.babynames.names.domain.datasourceAbstraction.NamesDataSource
import com.babynames.names.domain.entities.Name
import com.babynames.names.domain.exceptions.GetNamesException
import com.babynames.names.domain.repositoryAbstractions.NamesRepository
import io.reactivex.Observable
import javax.inject.Inject

class NamesRepositoryImpl @Inject constructor(private val namesDataSource: NamesDataSource) : NamesRepository {

    override fun getNames(type: String): Observable<List<Name>> = this.namesDataSource.getNames(type)
            .map {
                if (it.success == "Names found") {
                    it.data.map {
                        val name = Name(it.id, it.name, it.gender, it.meaning, it.origin)
                        name
                    }
                } else {
                    throw GetNamesException(GetNamesException.Type.GET_NAMES_ERROR)
                }
            }

//    override fun inserNameToFavorite(nameToFavoritesEntity: FavoritesEntity): Observable<Boolean> {
//
//    }
}