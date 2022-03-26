package com.xlwe.randomuser.di.data

import com.xlwe.randomuser.data.database.UserDao
import com.xlwe.randomuser.data.datasource.LocalDataSource
import com.xlwe.randomuser.data.datasource.RemoteDataSource
import com.xlwe.randomuser.data.datasource.UserLocalDataSource
import com.xlwe.randomuser.data.datasource.UserRemoteDataSource
import com.xlwe.randomuser.data.mapper.UserMapper
import com.xlwe.randomuser.data.network.ApiRequests
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DataSourceModule {

    @Provides
    fun provideRemoteDataSource(apiRequests: ApiRequests, userMapper: UserMapper): RemoteDataSource =
        UserRemoteDataSource(apiRequests, userMapper)

    @Provides
    fun provideLocalDataSource(userDao: UserDao, userMapper: UserMapper): LocalDataSource =
        UserLocalDataSource(userDao, userMapper)

}