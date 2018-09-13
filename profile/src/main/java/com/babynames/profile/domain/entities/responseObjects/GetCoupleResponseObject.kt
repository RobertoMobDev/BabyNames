package com.babynames.profile.domain.entities.responseObjects

import com.babynames.login.domain.entities.responseObjects.UserProfileResponse
import com.google.gson.annotations.SerializedName

data class GetCoupleResponseObject(@SerializedName("success") private val _success: String?,
                                   @SerializedName("data") private val _data: ArrayList<UserProfileResponse>?) {

    val success: String
        get() = this._success ?: ""

    val data: ArrayList<UserProfileResponse>
        get() = this._data ?: arrayListOf()

}