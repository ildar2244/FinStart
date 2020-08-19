package ru.axdar.finstart.models

sealed class Response<out T> {

    class Loading<T> : Response<T>()
    data class Success<out T>(val value: T): Response<T>()
    data class Error<out T>(val exception: T): Response<T>()

    companion object {
        fun <T> loading() = Loading<T>()
        fun <T> success(data: T): Response<T> =
            Success(data)
        fun <T> error(e: T): Response<T> =
            Error(e)
    }
}