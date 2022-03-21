package com.xlwe.randomuser.data.repositories

import com.xlwe.randomuser.domain.repositories.UserRepository
import com.xlwe.randomuser.domain.result.NetworkResult
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl : UserRepository {
    override fun getUser(): Flow<NetworkResult> {
        TODO()
    }
}