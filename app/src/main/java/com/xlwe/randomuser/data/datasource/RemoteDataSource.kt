package com.xlwe.randomuser.data.datasource

import com.xlwe.randomuser.domain.result.NetworkResult

interface RemoteDataSource {
    suspend fun getUser(): NetworkResult
}