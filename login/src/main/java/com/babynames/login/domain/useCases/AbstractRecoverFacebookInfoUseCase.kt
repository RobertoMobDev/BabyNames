package com.babynames.login.domain.useCases

import com.babynames.core.domain.executors.PostExecutionThread
import com.babynames.core.domain.executors.ThreadExecutor
import com.babynames.core.domain.useCases.UseCase
import com.babynames.login.domain.entities.requestObjects.SignInRequestObject
import com.babynames.login.domain.exceptions.SignInException
import com.facebook.GraphRequest
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class AbstractRecoverFacebookInfoUseCase(threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread) : UseCase<SignInRequestObject, GraphRequest>(threadExecutor, postExecutionThread) {
    override fun execute(observer: DisposableObserver<SignInRequestObject>, params: GraphRequest) {
        val observable: Observable<SignInRequestObject> = Observable.defer {
            Observable.just(params.executeAndWait())
        }.subscribeOn(Schedulers.from(this.threadExecutor))
                .observeOn(this.postExecutionThread.getScheduler()).map {
                    if (it.error != null) {
                        throw SignInException(SignInException.Type.FACEBOOK_GRAPH_ERROR, it.error.errorMessage)
                    } else {
                        val json = it.jsonObject
                        val userAge = json.getJSONObject("age_range")?.getString("min") ?: "0"
                        val pic = json.getJSONObject("picture")
                        val picUrl = pic.getJSONObject("data")?.getString("url") ?: ""
                        val signInRequestObject = SignInRequestObject("CreateUser",
                                json.getString("id"),
                                json.getString("email") ?: "",
                                picUrl,
                                if (json.has("gender")) json.getString("gender") else "",
                                userAge,
                                json.getString("name") ?: "")
                        signInRequestObject
                    }
                }
        this.disposables.add(observable.subscribeWith(observer))
    }

    override fun createObservable(params: GraphRequest): Observable<SignInRequestObject> {
        return Observable.just(params.executeAndWait()).map {
            if (it.error != null) {
                throw SignInException(SignInException.Type.FACEBOOK_GRAPH_ERROR, it.error.errorMessage)
            } else {
                val json = it.jsonObject
                val age = json.getJSONObject("user_age")
                val userAge = age.getString("min")
                val pic = json.getJSONObject("picture")
                val picData = pic.getJSONObject("data")
                val picUrl = picData.getString("url")
                val signInRequestObject = SignInRequestObject("",
                        json.getString("id"),
                        json.getString("email"),
                        picUrl,
                        json.getString("gender"),
                        userAge,
                        json.getString("name"))
                signInRequestObject
            }
        }
    }
}