package com.babynames.login.domain.useCases

import com.babynames.core.domain.executors.PostExecutionThread
import com.babynames.core.domain.executors.ThreadExecutor
import com.babynames.core.domain.useCases.UseCase
import com.babynames.login.domain.entities.requestObjects.SignInRequestObject
import com.babynames.login.domain.exceptions.SignInException
import com.facebook.GraphRequest
import io.reactivex.Observable
import javax.inject.Inject

class RecoverFacebookInfoUseCase @Inject constructor(threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread) : UseCase<SignInRequestObject, GraphRequest>(threadExecutor, postExecutionThread) {
    override fun createObservable(params: GraphRequest): Observable<SignInRequestObject> {
        return Observable.defer {
            Observable.just(params.executeAndWait()).map {
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
        }
    }
}

