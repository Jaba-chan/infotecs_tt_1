package ru.evgenykuzakov.domain.repository

import ru.evgenykuzakov.domain.model.User

interface RemoteUserRepository {

    suspend fun getUsers(): List<User>

}