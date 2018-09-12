package com.babynames.profile.domain.entities.responseObjects

import com.google.gson.annotations.SerializedName

data class CoupleResponseObject(@SerializedName("success") private val _success: String?,
                                @SerializedName("id") private val _id: String?) {

    val success: String
        get() = this._success ?: ""

    val id: String
        get() = this._id ?: ""
}