package com.babynames.login.domain.entities.requestObjects

data class SignInRequestObject(val type: String, val facebookId: String, val email: String,
                               val picture: String, val gender: String, val age: String, val name: String)