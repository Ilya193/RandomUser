package com.xlwe.randomuser.data.database.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_items")
data class UserItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val dob: String,
    val phone: String,
    val country: String,
    val city: String,
    val state: String,
    val latitude: String,
    val longitude: String
)