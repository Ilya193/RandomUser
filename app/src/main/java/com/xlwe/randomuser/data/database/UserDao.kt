package com.xlwe.randomuser.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.xlwe.randomuser.data.database.model.UserItemDbModel

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(userItemDbModel: UserItemDbModel)

}