package com.babynames.login.domain.entities

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
)