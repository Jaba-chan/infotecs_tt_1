package ru.evgenykuzakov.data.repository

import jakarta.inject.Inject
import ru.evgenykuzakov.data.util.toDomain
import ru.evgenykuzakov.data.util.toEntity
import ru.evgenykuzakov.data.util.toUserMainInfo
import ru.evgenykuzakov.database.dao.UsersDao
import ru.evgenykuzakov.domain.model.User
import ru.evgenykuzakov.domain.model.UserMainInfo
import ru.evgenykuzakov.domain.repository.LocalUsersRepository

class LocalUsersRepositoryImpl @Inject constructor(
    private val dao: UsersDao
) : LocalUsersRepository {

    private var cachedUsers = emptyList<UserMainInfo>()

    override suspend fun getUsers(): List<UserMainInfo> {
        return cachedUsers.ifEmpty {
            dao.getAllUsers().map { it.toUserMainInfo() }
        }
    }

    override suspend fun getUserDetailInfo(userId: Long): User {
        return dao.getUser(userId).toDomain()
    }

    override suspend fun clearAll() {
        cachedUsers = emptyList()
        dao.clearAll()
    }

    override suspend fun insertAll(users: List<User>) {
        dao.insertAll(users.map { it.toEntity() })
    }
}