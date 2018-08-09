package com.babynames.login.domain.exceptions

class CreateLocalAccountException @JvmOverloads constructor(val validationType: CreateLocalAccountException.Type, message: String? = "Create local account error", cause: Throwable? = null) : Throwable(message, cause) {
    enum class Type {
        ACCOUNT_TYPE_IS_NULL_OR_EMPTY,
        UNABLE_TO_CREATE_ACCOUNT_ERROR
    }
}