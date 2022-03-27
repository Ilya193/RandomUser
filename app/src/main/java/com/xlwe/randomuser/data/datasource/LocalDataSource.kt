package com.xlwe.randomuser.data.datasource

import com.xlwe.randomuser.data.database.model.UserItemDbModel
import com.xlwe.randomuser.domain.models.User
import com.xlwe.randomuser.domain.result.Response
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun addUser(userItemDbModel: UserItemDbModel)
    fun getUsers(): Flow<Response<User>>
}