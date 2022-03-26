package com.xlwe.randomuser.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xlwe.randomuser.data.database.model.UserItemDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(userItemDbModel: UserItemDbModel)

    @Query("SELECT * FROM user_items")
    fun getUsers(): Flow<List<UserItemDbModel>>

}