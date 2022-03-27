package com.xlwe.randomuser.domain.usecases

import com.xlwe.randomuser.domain.repositories.UserRepository
import com.xlwe.randomuser.domain.result.Response
import kotlinx.coroutines.flow.Flow

class GetUsersUseCase(
    private val userRepository: UserRepository
) {

    fun getUsers(): Flow<Response> {
        return userRepository.getUsers()
    }

}