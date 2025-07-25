package ru.evgenykuzakov.users.user_detail_info

import ru.evgenykuzakov.common.Resource
import ru.evgenykuzakov.domain.model.User

data class UserDetailInfoUIState(
    val user: Resource<User> = Resource.Loading()
)