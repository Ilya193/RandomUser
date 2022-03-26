package com.xlwe.randomuser.di.domain

import com.xlwe.randomuser.domain.repositories.UserRepository
import com.xlwe.randomuser.domain.usecases.GetUserUseCase
import com.xlwe.randomuser.domain.usecases.GetUsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun provideGetUserUseCase(userRepository: UserRepository): GetUserUseCase =
        GetUserUseCase(userRepository)

    @Provides
    fun provideGetUsersUseCase(userRepository: UserRepository): GetUsersUseCase =
        GetUsersUseCase(userRepository)

}