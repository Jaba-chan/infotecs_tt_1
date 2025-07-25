package ru.evgenykuzakov.domain.use_case

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.evgenykuzakov.common.Resource
import ru.evgenykuzakov.common.exception.ErrorMapper
import ru.evgenykuzakov.domain.model.UserMainInfo
import ru.evgenykuzakov.domain.repository.DefaultUserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: DefaultUserRepository,
    private val errorMapper: ErrorMapper
) {
    operator fun invoke(): Flow<Resource<List<UserMainInfo>>> =
        flow {
            emit(Resource.Loading())
            emit(Resource.Success(repository.getUsers()))
        }.catch { e ->
            emit(Resource.Error(message = errorMapper.map(e)))
            println("GetUsersUseCase" + e.message)
        }.flowOn(Dispatchers.IO)
}