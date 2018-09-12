package com.babynames.core.domain.entities

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable

data class UserProfile(
        val id: String,
        val facebookId: String,
        val email: String,
        val profileImage: String,
        val premium: String,
        val gender: String,
        val age: String,
        val name: String,
        val coupleId: String
) : Parcelable {
    val bundle: Bundle
        get() {
            val b = Bundle()
            b.putString("id", this.id)
            b.putString("facebookId", this.facebookId)
            b.putString("email", this.email)
            b.putString("profileImage", this.profileImage)
            b.putString("premium", this.premium)
            b.putString("gender", this.gender)
            b.putString("age", this.age)
            b.putString("name", this.name)
            b.putString("coupleId", this.coupleId)
            return b
        }

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(facebookId)
        parcel.writeString(email)
        parcel.writeString(profileImage)
        parcel.writeString(premium)
        parcel.writeString(gender)
        parcel.writeString(age)
        parcel.writeString(name)
        parcel.writeString(coupleId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserProfile> {
        override fun createFromParcel(parcel: Parcel): UserProfile {
            return UserProfile(parcel)
        }

        override fun newArray(size: Int): Array<UserProfile?> {
            return arrayOfNulls(size)
        }
    }


}