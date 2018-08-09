package com.babynames.login.domain.exceptions

class SignInException @JvmOverloads constructor(val validationType: Type, message: String? = "Sign In validation error", cause: Throwable? = null) : Throwable(message, cause) {
    enum class Type {
        PROFILE_NULL_ERROR,
        FACEBOOK_ID_ERROR,
        FACEBOOK_GRAPH_ERROR
    }
}