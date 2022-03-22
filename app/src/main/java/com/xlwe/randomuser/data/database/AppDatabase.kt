package com.xlwe.randomuser.data.database

import androidx.room.Database
import com.xlwe.randomuser.data.database.model.UserItemDbModel

@Database(entities = [UserItemDbModel::class], version = 1, exportSchema = false)
abstract class AppDatabase {
    abstract fun userDao(): UserDao
}