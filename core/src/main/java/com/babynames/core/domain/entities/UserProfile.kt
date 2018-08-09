package com.babynames.core.domain.entities

import android.os.Bundle

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
) {
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

    var accountType: String? = null
}