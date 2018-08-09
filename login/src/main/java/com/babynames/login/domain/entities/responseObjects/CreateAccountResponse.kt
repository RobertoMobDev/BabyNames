package com.babynames.login.domain.entities.responseObjects

import com.google.gson.annotations.SerializedName

data class CreateAccountResponse(
        @SerializedName("id") val _id: String?,
        @SerializedName("success") private val _success: String?,
        @SerializedName("data") private val _data: List<UserProfileResponse>?
) {

    val id: String
        get() = this._id ?: ""

    val success: String
        get() = this._success ?: ""

    val data: List<UserProfileResponse>
        get() = this._data ?: emptyList()

}