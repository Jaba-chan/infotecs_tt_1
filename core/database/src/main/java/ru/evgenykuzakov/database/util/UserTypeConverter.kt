package ru.evgenykuzakov.database.util

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json
import ru.evgenykuzakov.database.model.*

internal object UserTypeConverter {
    private val json = Json { ignoreUnknownKeys = true}

    @TypeConverter
    fun fromName(value: NameEntity) = json.encodeToString(value)
    @TypeConverter
    fun toName(value: String): NameEntity = json.decodeFromString(value)

    @TypeConverter
    fun fromLocation(value: LocationEntity) = json.encodeToString(value)
    @TypeConverter
    fun toLocation(value: String): LocationEntity = json.decodeFromString(value)

    @TypeConverter
    fun fromStreet(value: StreetEntity) = json.encodeToString(value)
    @TypeConverter
    fun toStreet(value: String): StreetEntity = json.decodeFromString(value)

    @TypeConverter
    fun fromCoordinates(value: CoordinatesEntity) = json.encodeToString(value)
    @TypeConverter
    fun toCoordinates(value: String): CoordinatesEntity = json.decodeFromString(value)

    @TypeConverter
    fun fromTimezone(value: TimezoneEntity) = json.encodeToString(value)
    @TypeConverter
    fun toTimezone(value: String): TimezoneEntity = json.decodeFromString(value)

    @TypeConverter
    fun fromLogin(value: LoginEntity) = json.encodeToString(value)
    @TypeConverter
    fun toLogin(value: String): LoginEntity = json.decodeFromString(value)

    @TypeConverter
    fun fromDob(value: DobEntity) = json.encodeToString(value)
    @TypeConverter
    fun toDob(value: String): DobEntity = json.decodeFromString(value)

    @TypeConverter
    fun fromRegistered(value: RegisteredEntity) = json.encodeToString(value)
    @TypeConverter
    fun toRegistered(value: String): RegisteredEntity = json.decodeFromString(value)

    @TypeConverter
    fun fromPicture(value: PictureEntity) = json.encodeToString(value)
    @TypeConverter
    fun toPicture(value: String): PictureEntity = json.decodeFromString(value)

    @TypeConverter
    fun fromId(value: IdEntity) = json.encodeToString(value)
    @TypeConverter
    fun toId(value: String): IdEntity = json.decodeFromString(value)

}