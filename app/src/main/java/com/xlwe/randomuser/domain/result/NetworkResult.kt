package com.xlwe.randomuser.domain.result

import com.xlwe.randomuser.domain.models.User

sealed class NetworkResult(
    val result: User? = null,
    val status: Status? = null
) {
    class Loading : NetworkResult()
    class Success(val user: User) : NetworkResult(result = user)
    class Error(val s: Status) : NetworkResult(status = s)
}