package ru.evgenykuzakov.users.show_users

import ru.evgenykuzakov.common.Resource
import ru.evgenykuzakov.domain.model.UserMainInfo

data class ShowScreenUIState(
    val users: Resource<List<UserMainInfo>> = Resource.Loading()
)