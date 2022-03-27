package com.xlwe.randomuser.data.repositories

import com.xlwe.randomuser.data.datasource.LocalDataSource
import com.xlwe.randomuser.data.datasource.RemoteDataSource
import com.xlwe.randomuser.data.mapper.UserMapper
import com.xlwe.randomuser.domain.models.User
import com.xlwe.randomuser.domain.repositories.UserRepository
import com.xlwe.randomuser.domain.result.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class UserRepositoryImpl @Inject constructor(
    private val mapper: UserMapper,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val coroutineContext: CoroutineContext
) : UserRepository {

    override fun getUser(): Flow<Response> = flow {
        val result = remoteDataSource.getUser()
        emit(result)
        result.result?.let { addUser(it) }
    }.flowOn(coroutineContext)

    override fun getUsers(): Flow<Response> = flow {
        localDataSource.getUsers().collect {
            emit(it)
        }
    }

    override suspend fun addUser(user: User) {
        localDataSource.addUser(mapper.mapNetworkModelToDb(user))
    }

}