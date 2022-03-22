package com.xlwe.randomuser.di.data

import com.xlwe.randomuser.data.mapper.UserMapper
import com.xlwe.randomuser.data.network.ApiRequests
import com.xlwe.randomuser.data.repositories.UserRepositoryImpl
import com.xlwe.randomuser.domain.repositories.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun provideUserMapper(): UserMapper =
        UserMapper()

    @Provides
    fun provideUserRepository(
        apiRequests: ApiRequests,
        userMapper: UserMapper
    ): UserRepository =
        UserRepositoryImpl(apiRequests, userMapper)

}