package com.babynames.names.data.datasourceimplementations

import com.babynames.names.data.services.api.NamesService
import com.babynames.names.domain.datasourceAbstraction.NamesDataSource
import com.babynames.names.domain.entities.Name
import com.babynames.names.domain.entities.responseObjects.NameResponseObject
import com.babynames.names.domain.exceptions.GetNamesException
import io.reactivex.Observable
import javax.inject.Inject

class NamesApiDataSourceImpl @Inject constructor(private val namesService: NamesService) : NamesDataSource {

    override fun getNames(type: String): Observable<List<Name>> =
            this.namesService.getNames(type).map {
                if (it.success == "Names found") {
                    it.data.map {
                        val name = Name(it.id, it.name, it.gender, it.meaning, it.origin)
                        name
                    }
                } else {
                    throw GetNamesException(GetNamesException.Type.GET_NAMES_ERROR)
                }
            }

}