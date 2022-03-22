package com.xlwe.randomuser.data.repositories

import com.xlwe.randomuser.data.database.UserDao
import com.xlwe.randomuser.data.mapper.UserMapper
import com.xlwe.randomuser.data.network.ApiRequests
import com.xlwe.randomuser.data.network.model.UserDTO
import com.xlwe.randomuser.domain.repositories.UserRepository
import com.xlwe.randomuser.domain.result.NetworkResult
import com.xlwe.randomuser.domain.result.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class UserRepositoryImpl @Inject constructor(
    private val apiRequests: ApiRequests,
    private val mapper: UserMapper,
    private val userDao: UserDao,
    private val coroutineContext: CoroutineContext
) : UserRepository {


    override fun getUser(): Flow<NetworkResult> = flow {
        try {
            val user = apiRequests.getUser()
            if (user.isSuccessful) {
                if (user.body() != null) {
                    addUser(user.body()!!)
                    emit(NetworkResult.Success(mapper.mapNetworkModelToEntity(user.body()!!)))
                }
                else {
                    emit(NetworkResult.Error(Status.SERVICE_UNAVAILABLE))
                }
            }
            else {
                emit(NetworkResult.Error(Status.SERVICE_UNAVAILABLE))
            }
        } catch (e: Exception) {
            emit(NetworkResult.Error(Status.NO_CONNECTION))
        }
    }.flowOn(coroutineContext)

    override suspend fun addUser(userDTO: UserDTO) {
        userDao.addUser(mapper.mapNetworkModelToDb(userDTO))
    }


}