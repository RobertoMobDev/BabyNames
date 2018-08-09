package com.babynames.login.domain.exceptions

class CheckPermissionsException @JvmOverloads constructor(val validationType: CheckPermissionsException.Type, message: String? = "Check permissions error", cause: Throwable? = null) : Throwable(message, cause) {
    enum class Type {
        NEED_REQUEST_ACCOUNT_PERMISSIONS_RATIONALE,
        NEED_REQUEST_ACCOUNT_PERMISSIONS,
        CREATE_ACCOUNT_VALIDATION_ERROR,
        ACCOUNT_ALREADY_EXIST_VALIDATION_ERROR,
    }
}