package ru.evgenykuzakov.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ResultDto(
    val results: List<UserDto>,
    val info: InfoDto
)

@Serializable
data class UserDto(
    val gender: String,
    val name: NameDto,
    val location: LocationDto,
    val email: String,
    val login: LoginDto,
    val dob: DobDto,
    val registered: RegisteredDto,
    val phone: String,
    val cell: String,
    val id: IdDto,
    val picture: PictureDto,
    val nat: String
)

@Serializable
data class NameDto(
    val title: String,
    val first: String,
    val last: String
)

@Serializable
data class LocationDto(
    val street: StreetDto,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val coordinates: CoordinatesDto,
    val timezone: TimezoneDto
)

@Serializable
data class StreetDto(
    val number: Int,
    val name: String
)

@Serializable
data class CoordinatesDto(
    val latitude: Float,
    val longitude: Float,
)

@Serializable
data class TimezoneDto(
    val offset: String,
    val description: String,
)

@Serializable
data class LoginDto(
    val uuid: String,
    val username: String,
    val password: String,
    val salt: String,
    val md5: String,
    val sha1: String,
    val sha256: String,
)

@Serializable
data class DobDto(
    val date: String,
    val age: Int
)

@Serializable
data class RegisteredDto(
    val date: String,
    val age: Int
)

@Serializable
data class IdDto(
    val name: String,
    val value: String
)

@Serializable
data class PictureDto(
    val large: String,
    val medium: String,
    val thumbnail: String
)

@Serializable
data class InfoDto(
    val seed: String,
    val results: Int,
    val page: Int,
    val version: Float
)