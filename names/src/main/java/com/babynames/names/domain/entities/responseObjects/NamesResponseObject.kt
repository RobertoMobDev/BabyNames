package com.babynames.names.domain.entities.responseObjects

import com.google.gson.annotations.SerializedName

data class NamesResponseObject(@SerializedName("success") private val _success: String?,
                               @SerializedName("data") private val _data: List<NameResponseObject>?) {

    val success: String
        get() = this._success ?: ""

    val data: List<NameResponseObject>
        get() = this._data ?: emptyList()
}