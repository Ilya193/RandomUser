package com.xlwe.randomuser.data.repositories

import androidx.lifecycle.LiveData
import com.xlwe.randomuser.data.mapper.UserMapper
import com.xlwe.randomuser.data.network.ApiRequests
import com.xlwe.randomuser.domain.repositories.UserRepository
import com.xlwe.randomuser.domain.result.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiRequests: ApiRequests,
    private val mapper: UserMapper
) : UserRepository {
    override fun getUser(): Flow<NetworkResult> {
        TODO()
    }
}