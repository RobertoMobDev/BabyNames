package com.babynames.names.data.datasourceimplementations

import com.babynames.names.data.services.api.NamesService
import com.babynames.names.domain.datasourceAbstraction.NamesDataSource
import com.babynames.names.domain.entities.Name
import com.babynames.names.domain.entities.responseObjects.NameResponseObject
import com.babynames.names.domain.entities.responseObjects.NamesResponseObject
import com.babynames.names.domain.exceptions.GetNamesException
import io.reactivex.Observable
import javax.inject.Inject

class NamesApiDataSourceImpl constructor(private val namesService: NamesService) : NamesDataSource {

    override fun getNames(type: String): Observable<NamesResponseObject> =
            this.namesService.getNames(type)

}