package com.xlwe.randomuser.data.network

import com.xlwe.randomuser.data.network.model.UserDTO
import retrofit2.http.GET

interface ApiRequests {
    @GET("api")
    suspend fun getUser(): UserDTO
}