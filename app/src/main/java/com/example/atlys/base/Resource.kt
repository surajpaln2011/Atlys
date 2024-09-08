package com.example.atlys.base

import java.lang.Exception

sealed class Resource<T>(
    val rawMessage: String?
) {
    companion object {
        private const val NO_INTERNET = "Please check your internet connection and try again."
        private const val NO_RESULTS_TRY_LATER = "Something went wrong. Please try again later."
    }

    data class Success<T>(
        val data: T?,
        private val msg: String?
    ): Resource<T>(msg)

    data class Error<T>(
        val error: ResourceError?,
        private val msg: String?
    ): Resource<T>(msg) {

        val errorMessage: String
            get() {
                return if (error is ResourceError.ApiError && !rawMessage.isNullOrEmpty()) {
                    rawMessage
                } else if (error is ResourceError.ConnectionError) {
                    NO_INTERNET
                } else {
                    NO_RESULTS_TRY_LATER
                }
            }
    }

    val message: String?
        get() = if (this is Error) {
            errorMessage
        } else {
            rawMessage
        }
}

sealed class ResourceError {
    data object ApiError: ResourceError()
    data class ConnectionError(val exception: Exception): ResourceError()
    data class OtherError(val exception: Exception) : ResourceError()
}