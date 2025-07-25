package ru.evgenykuzakov.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.evgenykuzakov.database.dao.UsersDao
import ru.evgenykuzakov.database.model.UserEntity
import ru.evgenykuzakov.database.util.UserTypeConverter

@Database(
    entities = [UserEntity::class],
    version = 1
)
@TypeConverters(
    UserTypeConverter::class
)
internal abstract class UsersRoomDatabase: RoomDatabase() {
    abstract fun usersDao(): UsersDao
}