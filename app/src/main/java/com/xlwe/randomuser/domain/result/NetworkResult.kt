package com.xlwe.randomuser.domain.result

import com.xlwe.randomuser.domain.models.User

sealed class NetworkResult(
    val result: User? = null
) {
    class Loading(val user: User) : NetworkResult(user)
    class Success : NetworkResult()
}