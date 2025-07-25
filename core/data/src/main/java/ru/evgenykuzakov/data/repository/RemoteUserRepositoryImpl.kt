package ru.evgenykuzakov.data.repository

import ru.evgenykuzakov.common.exception.ApiException
import ru.evgenykuzakov.data.util.toDomain
import ru.evgenykuzakov.domain.model.User
import ru.evgenykuzakov.domain.repository.RemoteUserRepository
import ru.evgenykuzakov.network.RandomUserApi
import javax.inject.Inject

class RemoteUserRepositoryImpl @Inject constructor(
    private val api: RandomUserApi,
) : RemoteUserRepository {
    override suspend fun getUsers(): List<User> {
        val response = api.getUsers()

        if (response.isSuccessful){
            val users = response.body()?.results ?: throw ApiException("No users")
            return users.mapIndexed{pos, user -> user.toDomain(pos.toLong()) }
        }
        else {
            val errorBody = response.errorBody()?.string()
            throw ApiException(errorBody ?: "")
        }
    }
}
