package com.babynames.babynames.core.domain.useCases

import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver

interface Interactor<T, Params> {

    fun execute(observer: DisposableObserver<T>, params: Params)

    fun execute(params: Params): Observable<T>
}