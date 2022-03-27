package com.xlwe.randomuser.data.datasource

import com.xlwe.randomuser.data.mapper.UserMapper
import com.xlwe.randomuser.data.network.ApiRequests
import com.xlwe.randomuser.domain.result.Response
import com.xlwe.randomuser.domain.result.Status
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val apiRequests: ApiRequests,
    private val mapper: UserMapper
) : RemoteDataSource {
    override suspend fun getUser(): Response {
        return try {
            val user = apiRequests.getUser()
            if (user.body() != null) {
                Response.Success(mapper.mapNetworkModelToEntity(user.body()!!))
            }
            else {
                Response.Error(Status.SERVICE_UNAVAILABLE)
            }
        } catch (e: Exception) {
            Response.Error(Status.NO_CONNECTION)
        }
    }
}