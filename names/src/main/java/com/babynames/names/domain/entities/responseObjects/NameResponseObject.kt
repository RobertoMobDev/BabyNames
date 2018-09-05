package com.babynames.names.domain.entities.responseObjects

import com.google.gson.annotations.SerializedName

data class NameResponseObject(@SerializedName("id") private val _id: String?,
                              @SerializedName("name") private val _name: String?,
                              @SerializedName("gender") private val _gender: String?,
                              @SerializedName("meaning") private val _meaning: String?,
                              @SerializedName("origin") private val _origin: String?) {

    val id: String
        get() = this._id ?: ""

    val name: String
        get() = this._name ?: ""

    val gender: String
        get() = this._gender ?: ""

    val meaning: String
        get() = this._meaning ?: ""

    val origin: String
        get() = this._origin ?: ""
}