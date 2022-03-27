package com.xlwe.randomuser.data.datasource

import com.xlwe.randomuser.domain.result.Response

interface RemoteDataSource {
    suspend fun getUser(): Response
}