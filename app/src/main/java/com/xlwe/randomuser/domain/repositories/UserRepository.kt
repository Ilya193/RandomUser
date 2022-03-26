package com.xlwe.randomuser.domain.repositories

import com.xlwe.randomuser.domain.models.User
import com.xlwe.randomuser.domain.result.NetworkResult
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUser(): Flow<NetworkResult>
    fun getUsers(): Flow<NetworkResult>
    suspend fun addUser(user: User)

}