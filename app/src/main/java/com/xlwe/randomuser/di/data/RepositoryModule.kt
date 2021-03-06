package com.xlwe.randomuser.di.data

import com.xlwe.randomuser.data.database.UserDao
import com.xlwe.randomuser.data.datasource.LocalDataSource
import com.xlwe.randomuser.data.datasource.RemoteDataSource
import com.xlwe.randomuser.data.mapper.UserMapper
import com.xlwe.randomuser.data.network.ApiRequests
import com.xlwe.randomuser.data.repositories.UserRepositoryImpl
import com.xlwe.randomuser.domain.repositories.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun provideUserMapper(): UserMapper =
        UserMapper()

    @Provides
    fun provideCoroutineContext(): CoroutineContext =
        Dispatchers.IO

    @Provides
    fun provideUserRepository(
        userMapper: UserMapper,
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
        coroutineContext: CoroutineContext
    ): UserRepository =
        UserRepositoryImpl(userMapper, remoteDataSource, localDataSource, coroutineContext)

}