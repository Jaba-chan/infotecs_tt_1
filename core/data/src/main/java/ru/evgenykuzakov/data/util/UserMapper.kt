package ru.evgenykuzakov.data.util

import ru.evgenykuzakov.database.model.*
import ru.evgenykuzakov.domain.model.*
import ru.evgenykuzakov.network.model.*

fun User.toUserMainInfo() = UserMainInfo(
    id = id,
    phone = phone,
    name = name,
    location = location.toLocationMainInfo(),
    picture = picture.toPictureMedium()
)

fun Location.toLocationMainInfo() = LocationMainInfo(
    street = street,
    city = city,
    country = country
)

fun Picture.toPictureMedium() = PictureMedium(
    large = large
)

fun UserDto.toDomain(userId: Long) = User(
    id = userId,
    gender = gender,
    name = name.toDomain(),
    location = location.toDomain(),
    email = email,
    login = login.toDomain(),
    dob = dob.toDomain(),
    registered = registered.toDomain(),
    phone = phone,
    cell = cell,
    idCard = id.toDomain(),
    picture = picture.toDomain(),
    nat = nat
)

fun NameDto.toDomain() = Name(
    title = title,
    first = first,
    last = last
)

fun LocationDto.toDomain() = Location(
    street = street.toDomain(),
    city = city,
    state = state,
    country = country,
    postcode = postcode,
    coordinates = coordinates.toDomain(),
    timezone = timezone.toDomain()
)

fun StreetDto.toDomain() = Street(
    number = number,
    name = name
)

fun CoordinatesDto.toDomain() = Coordinates(
    latitude = latitude,
    longitude = longitude
)

fun TimezoneDto.toDomain() = Timezone(
    offset = offset,
    description = description
)

fun LoginDto.toDomain() = Login(
    uuid = uuid,
    username = username,
    password = password,
    salt = salt,
    md5 = md5,
    sha1 = sha1,
    sha256 = sha256
)

fun DobDto.toDomain() = Dob(
    date = date,
    age = age
)

fun IdDto.toDomain() = Id(
    name = name,
    value = value
)

fun RegisteredDto.toDomain() = Registered(
    date = date,
    age = age
)

fun PictureDto.toDomain() = Picture(
    large = large,
    medium = medium,
    thumbnail = thumbnail
)

fun UserEntity.toDomain() = User(
    id = id,
    gender = gender,
    name = name.toDomain(),
    location = location.toDomain(),
    email = email,
    login = login.toDomain(),
    dob = dob.toDomain(),
    registered = registered.toDomain(),
    phone = phone,
    cell = cell,
    idCard = idCard.toDomain(),
    picture = picture.toDomain(),
    nat = nat
)
fun NameEntity.toDomain() = Name(
    title = title,
    first = first,
    last = last
)

fun LocationEntity.toDomain() = Location(
    street = street.toDomain(),
    city = city,
    state = state,
    country = country,
    postcode = postcode,
    coordinates = coordinates.toDomain(),
    timezone = timezone.toDomain()
)

fun StreetEntity.toDomain() = Street(
    number = number,
    name = name
)

fun CoordinatesEntity.toDomain() = Coordinates(
    latitude = latitude,
    longitude = longitude
)

fun TimezoneEntity.toDomain() = Timezone(
    offset = offset,
    description = description
)

fun LoginEntity.toDomain() = Login(
    uuid = uuid,
    username = username,
    password = password,
    salt = salt,
    md5 = md5,
    sha1 = sha1,
    sha256 = sha256
)

fun DobEntity.toDomain() = Dob(
    date = date,
    age = age
)

fun IdEntity.toDomain() = Id(
    name = name,
    value = value
)

fun RegisteredEntity.toDomain() = Registered(
    date = date,
    age = age
)

fun PictureEntity.toDomain() = Picture(
    large = large,
    medium = medium,
    thumbnail = thumbnail
)

fun UserEntity.toUserMainInfo() = UserMainInfo(
    id = id,
    name = name.toDomain(),
    location = location.toLocationMainInfo(),
    phone = phone,
    picture = picture.toPictureMedium(),
)

fun LocationEntity.toLocationMainInfo() = LocationMainInfo(
    street = street.toDomain(),
    city = city,
    country = country
)

fun PictureEntity.toPictureMedium() = PictureMedium(
    large = large
)

fun User.toEntity() = UserEntity(
    id = id,
    gender = gender,
    name = name.toEntity(),
    location = location.toEntity(),
    email = email,
    login = login.toEntity(),
    dob = dob.toEntity(),
    registered = registered.toEntity(),
    phone = phone,
    cell = cell,
    idCard = idCard.toEntity(),
    picture = picture.toEntity(),
    nat = nat
)

fun Name.toEntity() = NameEntity(
    title = title,
    first = first,
    last = last
)

fun Location.toEntity() = LocationEntity(
    street = street.toEntity(),
    city = city,
    state = state,
    country = country,
    postcode = postcode,
    coordinates = coordinates.toEntity(),
    timezone = timezone.toEntity()
)

fun Street.toEntity() = StreetEntity(
    number = number,
    name = name
)

fun Coordinates.toEntity() = CoordinatesEntity(
    latitude = latitude,
    longitude = longitude
)

fun Timezone.toEntity() = TimezoneEntity(
    offset = offset,
    description = description
)

fun Login.toEntity() = LoginEntity(
    uuid = uuid,
    username = username,
    password = password,
    salt = salt,
    md5 = md5,
    sha1 = sha1,
    sha256 = sha256
)

fun Dob.toEntity() = DobEntity(
    date = date,
    age = age
)

fun Id.toEntity() = IdEntity(
    name = name,
    value = value
)

fun Registered.toEntity() = RegisteredEntity(
    date = date,
    age = age
)

fun Picture.toEntity() = PictureEntity(
    large = large,
    medium = medium,
    thumbnail = thumbnail
)