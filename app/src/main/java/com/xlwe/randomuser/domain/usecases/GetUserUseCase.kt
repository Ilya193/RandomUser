package com.xlwe.randomuser.domain.usecases

import com.xlwe.randomuser.domain.repositories.UserRepository
import com.xlwe.randomuser.domain.result.NetworkResult
import kotlinx.coroutines.flow.Flow

class GetUserUseCase(
    private val userRepository: UserRepository
) {
    fun getUser(): Flow<NetworkResult> {
        return userRepository.getUser()
    }
}