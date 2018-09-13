package com.babynames.profile.domain.exceptions

class SetCoupleException @JvmOverloads constructor(val validationType: SetCoupleException.Type, message: String? = "Error setting couple", cause: Throwable? = null) : Throwable(message, cause) {

    enum class Type {
        SET_COUPLE_ERROR
    }

}