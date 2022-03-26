package com.xlwe.randomuser.data.datasource

import com.xlwe.randomuser.data.mapper.UserMapper
import com.xlwe.randomuser.data.network.ApiRequests
import com.xlwe.randomuser.domain.result.NetworkResult
import com.xlwe.randomuser.domain.result.Status
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val apiRequests: ApiRequests,
    private val mapper: UserMapper
) : RemoteDataSource {
    override suspend fun getUser(): NetworkResult {
        return try {
            val user = apiRequests.getUser()
            if (user.body() != null) {
                NetworkResult.Success(mapper.mapNetworkModelToEntity(user.body()!!))
            }
            else {
                NetworkResult.Error(Status.SERVICE_UNAVAILABLE)
            }
        } catch (e: Exception) {
            NetworkResult.Error(Status.NO_CONNECTION)
        }
    }
}