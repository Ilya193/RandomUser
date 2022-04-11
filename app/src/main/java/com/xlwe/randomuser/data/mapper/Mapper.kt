package com.xlwe.randomuser.data.mapper

import com.xlwe.randomuser.data.database.model.UserItemDbModel
import com.xlwe.randomuser.data.network.model.UserDTO
import com.xlwe.randomuser.domain.models.User

interface Mapper {
    fun mapNetworkModelToEntity(userDTO: UserDTO): User
    fun mapNetworkModelToDb(user: User): UserItemDbModel
    fun mapDbModelToEntity(userItemDbModel: List<UserItemDbModel>): User
}