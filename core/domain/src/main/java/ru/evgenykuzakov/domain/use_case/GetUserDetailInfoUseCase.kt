package ru.evgenykuzakov.domain.use_case

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.evgenykuzakov.common.Resource
import ru.evgenykuzakov.domain.model.User
import ru.evgenykuzakov.domain.repository.DefaultUserRepository
import javax.inject.Inject

class GetUserDetailInfoUseCase @Inject constructor(
    private val repository: DefaultUserRepository
) {
    suspend operator fun invoke(userId: Long): Flow<Resource<User>> = flow{
        emit(Resource.Loading())
        emit(Resource.Success(repository.getUserDetailInfo(userId)))
    }.catch { e ->
        println("GetUserDetailInfoUseCase" + e.message)
        println(userId.toString())
        emit(Resource.Error(message = e.message))
    }.flowOn(Dispatchers.IO)
}