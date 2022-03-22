package com.xlwe.randomuser.di.data

import android.content.Context
import androidx.room.Room
import com.xlwe.randomuser.data.database.AppDatabase
import com.xlwe.randomuser.data.database.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        var instance: AppDatabase? = null

        synchronized(this) {
            instance?.let {
                return it
            }

            val db = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "user_items.db"
            ).build()
            instance = db
            return db
        }
    }

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): UserDao =
        appDatabase.userDao()

}