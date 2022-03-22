package com.xlwe.randomuser.data.mapper

import android.util.Log
import com.xlwe.randomuser.data.database.model.UserItemDbModel
import com.xlwe.randomuser.data.network.model.UserDTO
import com.xlwe.randomuser.domain.models.*

class UserMapper {

    fun mapNetworkModelToEntity(userDTO: UserDTO): User {
        val entityInfo = Info(
            page = userDTO.info.page,
            results =  userDTO.info.results,
            seed = userDTO.info.seed,
            version = userDTO.info.version
        )

        val entityResult = mutableListOf<Result>()

        userDTO.results.forEach {
            val tempDob = Dob(
                age = it.dob.age,
                date =  it.dob.date
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

            entityResult.add(Result(
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
            ))
        }

        return User(
            info = entityInfo,
            results = entityResult
        )
    }

    fun mapNetworkModelToDb(userDTO: UserDTO): UserItemDbModel {
        val userData = userDTO.results[0]
        return UserItemDbModel(
            name = userData.name.title + userData.name.first + userData.name.last,
            dob = userData.dob.date,
            age = userData.dob.age,
            phone = userData.phone,
            country = userData.location.country,
            city = userData.location.city,
            state = userData.location.state,
            latitude = userData.location.coordinates.latitude,
            longitude = userData.location.coordinates.longitude,
        )
    }

}