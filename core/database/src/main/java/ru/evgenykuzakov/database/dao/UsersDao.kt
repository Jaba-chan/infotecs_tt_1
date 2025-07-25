package ru.evgenykuzakov.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.evgenykuzakov.database.model.UserEntity

@Dao
interface UsersDao {

    @Query("SELECT * from users")
    fun getAllUsers(): List<UserEntity>

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUser(id: Long): UserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<UserEntity>)

    @Query("DELETE FROM users")
    fun clearAll()
}