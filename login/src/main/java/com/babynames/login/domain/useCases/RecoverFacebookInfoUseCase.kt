package com.babynames.login.domain.useCases

import android.os.Bundle
import com.babynames.core.domain.executors.PostExecutionThread
import com.babynames.core.domain.executors.ThreadExecutor
import com.babynames.core.domain.useCases.UseCase
import com.babynames.login.domain.entities.requestObjects.SignInRequestObject
import com.babynames.login.domain.exceptions.SignInException
import com.facebook.AccessToken
import com.facebook.GraphRequest
import io.reactivex.Observable
import javax.inject.Inject

class RecoverFacebookInfoUseCase @Inject constructor(threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread) : UseCase<SignInRequestObject, Bundle>(threadExecutor, postExecutionThread) {

    override fun createObservable(params: Bundle): Observable<SignInRequestObject> =
            Observable.just(
                    GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken()) { a, b ->

                    }.apply {
                        this.parameters = params
                    }.executeAndWait()
            ).map {
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