package com.xlwe.randomuser.data.mapper

import com.xlwe.randomuser.data.database.model.UserItemDbModel
import com.xlwe.randomuser.data.network.model.UserDTO
import com.xlwe.randomuser.domain.models.*

class UserMapper {

    fun mapNetworkModelToEntity(userDTO: UserDTO): User {
        val entityInfo = Info(
            page = userDTO.info.page,
            results = userDTO.info.results,
            seed = userDTO.info.seed,
            version = userDTO.info.version
        )

        val entityResult = mutableListOf<Result>()

        userDTO.results.forEach {
            val tempDob = Dob(
                age = it.dob.age,
                date = it.dob.date
            )

            val tempId = Id(
                name = it.id.name,
                value = it.id.value ?: ""
            )

            val tempLocation = Location(
                city = it.location.city,
                coordinates = Coordinates(
                    latitude = it.location.coordinates.latitude,
                    longitude = it.location.coordinates.longitude
                ),
                country = it.location.country,
                postcode = it.location.postcode,
                state = it.location.state,
                street = Street(
                    name = it.location.street.name,
                    number = it.location.street.number
                ),
                timezone = Timezone(
                    description = it.location.timezone.description,
                    offset = it.location.timezone.offset
                )
            )

            val tempLogin = Login(
                md5 = it.login.md5,
                password = it.login.password,
                salt = it.login.salt,
                sha1 = it.login.sha1,
                sha256 = it.login.sha256,
                username = it.login.username,
                uuid = it.login.uuid,
            )

            val tempName = Name(
                first = it.name.first,
                last = it.name.last,
                title = it.name.title,
            )

            val tempPicture = Picture(
                large = it.picture.large,
                medium = it.picture.medium,
                thumbnail = it.picture.thumbnail
            )

            val tempRegistered = Registered(
                age = it.registered.age,
                date = it.registered.date,
            )

            entityResult.add(
                Result(
                    cell = it.cell,
                    dob = tempDob,
                    email = it.email,
                    gender = it.gender,
                    id = tempId,
                    location = tempLocation,
                    login = tempLogin,
                    name = tempName,
                    nat = it.nat,
                    phone = it.phone,
                    picture = tempPicture,
                    registered = tempRegistered
                )
            )
        }

        return User(
            info = entityInfo,
            results = entityResult
        )
    }

    fun mapNetworkModelToDb(user: User): UserItemDbModel {
        val userData = user.results[0]
        return UserItemDbModel(
            title = userData.name.title,
            first = userData.name.first,
            last = userData.name.last,
            dob = userData.dob.date,
            age = userData.dob.age,
            phone = userData.phone,
            country = userData.location.country,
            city = userData.location.city,
            state = userData.location.state,
            picture = userData.picture.large,
            latitude = userData.location.coordinates.latitude,
            longitude = userData.location.coordinates.longitude,
        )
    }

    fun mapDbModelToEntity(userItemDbModel: List<UserItemDbModel>): User {
        val entityInfo = Info(
            page = 0,
            results = 0,
            seed = "",
            version = ""
        )

        val entityResult = mutableListOf<Result>()

        userItemDbModel.forEach {
            val tempDob = Dob(
                age = it.age,
                date = it.dob
            )

            val tempId = Id(
                name = "",
                value = ""
            )

            val tempLocation = Location(
                city = it.city,
                coordinates = Coordinates(
                    latitude = it.latitude,
                    longitude = it.longitude
                ),
                country = it.country,
                postcode = "",
                state = it.state,
                street = Street(
                    name = "",
                    number = 0
                ),
                timezone = Timezone(
                    description = "",
                    offset = ""
                )
            )

            val tempLogin = Login(
                md5 = "",
                password = "",
                salt = "",
                sha1 = "",
                sha256 = "",
                username = "",
                uuid = "",
            )

            val tempName = Name(
                first = it.first,
                last = it.last,
                title = it.title,
            )

            val tempPicture = Picture(
                large = it.picture,
                medium = "",
                thumbnail = ""
            )

            val tempRegistered = Registered(
                age = 0,
                date = "",
            )

            entityResult.add(
                Result(
                    cell = "",
                    dob = tempDob,
                    email = "",
                    gender = "",
                    id = tempId,
                    location = tempLocation,
                    login = tempLogin,
                    name = tempName,
                    nat = "",
                    phone = it.phone,
                    picture = tempPicture,
                    registered = tempRegistered
                )
            )
        }

        return User(
            info = entityInfo,
            results = entityResult
        )
    }

}