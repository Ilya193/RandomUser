package com.xlwe.randomuser.data.datasource

import com.xlwe.randomuser.data.database.UserDao
import com.xlwe.randomuser.data.database.model.UserItemDbModel
import com.xlwe.randomuser.data.mapper.UserMapper
import com.xlwe.randomuser.domain.result.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(
    private val userDao: UserDao,
    private val mapper: UserMapper
) : LocalDataSource {
    override suspend fun addUser(userItemDbModel: UserItemDbModel) {
        userDao.addUser(userItemDbModel)
    }

    override fun getUsers(): Flow<NetworkResult> = flow {
        userDao.getUsers().collect {
            emit(NetworkResult.Success(mapper.mapDbModelToEntity(it)))
        }
    }
}