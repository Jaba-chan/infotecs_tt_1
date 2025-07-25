package ru.evgenykuzakov.users.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.evgenykuzakov.common.exception.ErrorMapper
import ru.evgenykuzakov.users.show_users.placeholder.DefaultErrorMapper

@Module
@InstallIn(SingletonComponent::class)
abstract class UsersFeatureModule {
    @Binds
    internal abstract fun bindsCaughtError(
        impl: DefaultErrorMapper,
    ): ErrorMapper

}