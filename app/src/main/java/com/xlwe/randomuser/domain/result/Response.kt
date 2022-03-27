package com.xlwe.randomuser.domain.result

sealed class Response<T>(
    val result: T? = null,
    val status: Status? = null
) {
    class Loading<T> : Response<T>()
    class Success<T>(data: T) : Response<T>(result = data)
    class Error<T>(s: Status) : Response<T>(status = s)
}