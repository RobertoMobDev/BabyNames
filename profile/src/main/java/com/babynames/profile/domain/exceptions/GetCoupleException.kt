package com.babynames.profile.domain.exceptions

class GetCoupleException @JvmOverloads constructor(val validationType: GetCoupleException.Type, message: String? = "Error getting couple", cause: Throwable? = null) : Throwable(message, cause) {

    enum class Type {
        GET_COUPLE_ERROR
    }
}