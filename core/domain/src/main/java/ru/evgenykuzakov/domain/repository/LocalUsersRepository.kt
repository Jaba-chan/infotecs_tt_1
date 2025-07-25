package ru.evgenykuzakov.domain.repository

import ru.evgenykuzakov.domain.model.User
import ru.evgenykuzakov.domain.model.UserMainInfo

interface LocalUsersRepository {

    suspend fun getUsers(): List<UserMainInfo>

    suspend fun getUserDetailInfo(userId: Long): User

    suspend fun clearAll()

    suspend fun insertAll(users: List<User>)

}