package com.xlwe.randomuser.domain.repositories

import com.xlwe.randomuser.data.network.model.UserDTO
import com.xlwe.randomuser.domain.result.NetworkResult
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUser(): Flow<NetworkResult>
    suspend fun addUser(body: UserDTO)

}