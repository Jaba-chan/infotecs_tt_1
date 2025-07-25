package ru.evgenykuzakov.data.repository

import ru.evgenykuzakov.data.util.toUserMainInfo
import ru.evgenykuzakov.domain.model.User
import ru.evgenykuzakov.domain.model.UserMainInfo
import ru.evgenykuzakov.domain.repository.DefaultUserRepository
import ru.evgenykuzakov.domain.repository.LocalUsersRepository
import ru.evgenykuzakov.domain.repository.RemoteUserRepository
import javax.inject.Inject

class DefaultUserRepositoryImpl @Inject constructor(
    private val localDataSource: LocalUsersRepository,
    private val remoteDataSource: RemoteUserRepository,
) : DefaultUserRepository {

    override suspend fun getUsers(): List<UserMainInfo> {
        val localData = localDataSource.getUsers()
        return localData.ifEmpty {
            val remoteData = remoteDataSource.getUsers()
            localDataSource.insertAll(remoteData)
            return remoteData.map{it.toUserMainInfo() }
        }
    }

    override suspend fun getUserDetailInfo(userId: Long): User {
        return localDataSource.getUserDetailInfo(userId)
    }

    override suspend fun refreshUsers() {
        localDataSource.clearAll()
        val users = remoteDataSource.getUsers()
        localDataSource.insertAll(users)
    }
}