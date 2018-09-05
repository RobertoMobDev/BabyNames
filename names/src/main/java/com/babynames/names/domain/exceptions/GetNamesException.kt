package com.babynames.names.domain.exceptions

class GetNamesException @JvmOverloads constructor(val validationType: GetNamesException.Type, message: String? = "Error getting names", cause: Throwable? = null) : Throwable(message, cause) {

    enum class Type {
        GET_NAMES_ERROR
    }

}