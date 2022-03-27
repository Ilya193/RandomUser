package com.xlwe.randomuser.domain.repositories

import com.xlwe.randomuser.domain.models.User
import com.xlwe.randomuser.domain.result.Response
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUser(): Flow<Response<User>>
    fun getUsers(): Flow<Response<User>>
    suspend fun addUser(user: User)

}