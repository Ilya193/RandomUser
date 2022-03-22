package com.xlwe.randomuser.data.database.model

import androidx.room.Entity

@Entity(tableName = "user_items")
data class UserItemDbModel(
    val cell: String,
    val dob: Dob,
    val email: String,
    val gender: String,
    val id: Id,
    val location: Location,
    val login: Login,
    val name: Name,
    val nat: String,
    val phone: String,
    val picture: Picture,
    val registered: Registered
)