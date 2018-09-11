package com.babynames.login.domain.useCases

import com.babynames.core.domain.executors.PostExecutionThread
import com.babynames.core.domain.executors.ThreadExecutor
import com.babynames.core.domain.useCases.UseCase
import com.babynames.login.domain.entities.requestObjects.SignInRequestObject
import com.babynames.login.domain.exceptions.SignInException
import com.facebook.GraphRequest
import io.reactivex.Observable
import org.json.JSONObject
import javax.inject.Inject

class RecoverFacebookInfoUseCase @Inject constructor(threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread) : UseCase<SignInRequestObject, GraphRequest>(threadExecutor, postExecutionThread) {
    override fun createObservable(params: GraphRequest): Observable<SignInRequestObject> {
        return Observable.defer {
            Observable.just(params.executeAndWait()).map {
                if (it.error != null) {
                    throw SignInException(SignInException.Type.FACEBOOK_GRAPH_ERROR, it.error.errorMessage)
                } else {
                    val json = it.jsonObject

                    val id = if (json.has("id")) {json.getString("id") ?: ""} else {""}

                    val email = if (json.has("email")) {json.getString("email") ?: ""} else {""}

                    val gender = if (json.has("gender")) {json.getString("gender")} else {""}

                    val name = if (json.has("name")) {json.getString("name") ?: ""} else {""}

                    val userAge = if (json.has("age_range")) {json.getJSONObject("age_range")?.getString("min") ?: "0"} else {"0"}

                    val pic = if (json.has("picture")) {json.getJSONObject("picture")} else {JSONObject()}

                    val picData = if (pic.has("data")) {pic.getJSONObject("data")} else {JSONObject()}

                    val picUrl = if (picData.has("url")) {picData?.getString("url") ?: ""} else {""}

                    val signInRequestObject = SignInRequestObject("CreateUser",
                            id,
                            email,
                            picUrl,
                            gender,
                            userAge,
                            name)
                    signInRequestObject
                }
            }
        }
    }
}

