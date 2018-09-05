package com.babynames.names.domain.repositoryAbstractions

import com.babynames.names.domain.entities.responseObjects.NameResponseObject
import io.reactivex.Observable

interface NamesRepository {
    fun getNames(type: String): Observable<List<NameResponseObject>>
}