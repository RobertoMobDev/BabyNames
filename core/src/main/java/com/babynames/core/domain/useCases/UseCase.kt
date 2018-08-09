package com.babynames.core.domain.useCases

import com.babynames.core.domain.executors.PostExecutionThread
import com.babynames.core.domain.executors.ThreadExecutor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class UseCase<T, Params>(private val threadExecutor: ThreadExecutor, private val postExecutionThread: PostExecutionThread) : Interactor<T, Params> {

    abstract fun createObservable(params: Params): Observable<T>

    private val disposables: CompositeDisposable by lazy { CompositeDisposable() }

    override fun execute(observer: DisposableObserver<T>, params: Params) {
        val observable: Observable<T> = this.createObservable(params)
                .subscribeOn(Schedulers.from(this.threadExecutor))
                .observeOn(this.postExecutionThread.getScheduler())
        this.disposables.add(observable.subscribeWith(observer))
    }

    override fun execute(params: Params): Observable<T> = this.createObservable(params)

    fun dispose() {
        if (!this.disposables.isDisposed) {
            this.disposables.dispose()
        }
    }

}