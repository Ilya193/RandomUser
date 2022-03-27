package com.xlwe.randomuser.domain.result

import com.xlwe.randomuser.domain.models.User

sealed class Response(
    val result: User? = null,
    val status: Status? = null
) {
    class Loading : Response()
    class Success(val user: User) : Response(result = user)
    class Error(val s: Status) : Response(status = s)
}