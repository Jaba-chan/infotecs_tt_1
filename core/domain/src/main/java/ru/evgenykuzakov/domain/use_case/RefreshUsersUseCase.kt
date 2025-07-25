package ru.evgenykuzakov.domain.use_case

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.evgenykuzakov.common.Resource
import ru.evgenykuzakov.domain.repository.DefaultUserRepository
import javax.inject.Inject

class RefreshUsersUseCase @Inject constructor(
    private val repository: DefaultUserRepository
) {
    suspend operator fun invoke(): Flow<Resource<Unit>> = flow{
        emit(Resource.Loading())
        emit(Resource.Success(repository.refreshUsers()))
    }.catch { e ->
        println("RefreshUsersUseCase" + e.message)
        emit(Resource.Error(message = e.message))
    }.flowOn(Dispatchers.IO)
}