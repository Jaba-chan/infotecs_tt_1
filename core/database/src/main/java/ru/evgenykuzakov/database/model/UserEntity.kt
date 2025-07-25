package ru.evgenykuzakov.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "users")
data class UserEntity (
    @PrimaryKey(autoGenerate = false) val id: Long = 0,
    val gender: String,
    val name: NameEntity,
    val location: LocationEntity,
    val email: String,
    val login: LoginEntity,
    val dob: DobEntity,
    val registered: RegisteredEntity,
    val phone: String,
    val cell: String,
    val idCard: IdEntity,
    val picture: PictureEntity,
    val nat: String
)
@Serializable
data class NameEntity(
    val title: String,
    val first: String,
    val last: String
)
@Serializable
data class LocationEntity(
    val street: StreetEntity,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val coordinates: CoordinatesEntity,
    val timezone: TimezoneEntity
)
@Serializable
data class StreetEntity(
    val number: Int,
    val name: String
)
@Serializable
data class CoordinatesEntity(
    val latitude: Float,
    val longitude: Float,
)
@Serializable
data class TimezoneEntity(
    val offset: String,
    val description: String,
)
@Serializable
data class LoginEntity(
    val uuid: String,
    val username: String,
    val password: String,
    val salt: String,
    val md5: String,
    val sha1: String,
    val sha256: String,
)
@Serializable
data class DobEntity(
    val date: String,
    val age: Int
)
@Serializable
data class RegisteredEntity(
    val date: String,
    val age: Int
)
@Serializable
data class IdEntity(
    val name: String,
    val value: String?
)
@Serializable
data class PictureEntity(
    val large: String,
    val medium: String,
    val thumbnail: String
)