package com.babynames.profile.domain.entities.responseObjects

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class CoupleResponseObject(@SerializedName("success") private val _success: String?,
                                @SerializedName("id") private val _id: String?) : Parcelable {

    val success: String
        get() = this._success ?: ""

    val id: String
        get() = this._id ?: ""

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_success)
        parcel.writeString(_id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CoupleResponseObject> {
        override fun createFromParcel(parcel: Parcel): CoupleResponseObject {
            return CoupleResponseObject(parcel)
        }

        override fun newArray(size: Int): Array<CoupleResponseObject?> {
            return arrayOfNulls(size)
        }
    }
}