package com.babynames.login.domain.entities.responseObjects

import com.google.gson.annotations.SerializedName

data class UserProfileResponse(
        @SerializedName("id") private val _id: String?,
        @SerializedName("facebook_id") private val _facebookId: String?,
        @SerializedName("email") private val _email: String?,
        @SerializedName("profile_image") private val _profileImage: String?,
        @SerializedName("premium") private val _premium: String?,
        @SerializedName("gender") private val _gender: String?,
        @SerializedName("age") private val _age: String?,
        @SerializedName("name") private val _name: String?,
        @SerializedName("couple_id") private val _coupleId: String?
) {
    val id: String
        get() = this._id ?: ""

    val facebookId: String
        get() = this._facebookId ?: ""

    val email: String
        get() = this._email ?: ""

    val profileImage: String
        get() = this._profileImage ?: ""

    val premium: String
        get() = this._premium ?: ""

    val gender: String
        get() = this._gender ?: ""

    val age: String
        get() = _age ?: ""

    val name: String
        get() = _name ?: ""

    val coupleId: String
        get() = _coupleId ?: ""
}