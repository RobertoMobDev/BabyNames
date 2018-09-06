package com.babynames.names.domain.datasourceAbstraction

import com.babynames.names.domain.entities.responseObjects.NameResponseObject
import io.reactivex.Observable

interface NamesDataSource {
    fun getNames(type: String): Observable<ArrayList<NameResponseObject>>
}