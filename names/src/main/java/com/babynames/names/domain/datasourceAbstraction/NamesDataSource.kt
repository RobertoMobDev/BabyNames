package com.babynames.names.domain.datasourceAbstraction

import com.babynames.names.domain.entities.Name
import com.babynames.names.domain.entities.responseObjects.NameResponseObject
import com.babynames.names.domain.entities.responseObjects.NamesResponseObject
import io.reactivex.Observable

interface NamesDataSource {
    fun getNames(type: String): Observable<NamesResponseObject>
}