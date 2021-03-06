package com.xlwe.randomuser.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_items")
data class UserItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val first: String,
    val last: String,
    val dob: String,
    val age: Int,
    val phone: String,
    val picture: String,
    val country: String,
    val city: String,
    val state: String,
    val latitude: String,
    val longitude: String
)