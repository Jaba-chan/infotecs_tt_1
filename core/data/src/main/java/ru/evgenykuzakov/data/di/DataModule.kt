package ru.evgenykuzakov.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.evgenykuzakov.data.repository.DefaultUserRepositoryImpl
import ru.evgenykuzakov.data.repository.LocalUsersRepositoryImpl
import ru.evgenykuzakov.data.repository.RemoteUserRepositoryImpl
import ru.evgenykuzakov.domain.repository.DefaultUserRepository
import ru.evgenykuzakov.domain.repository.LocalUsersRepository
import ru.evgenykuzakov.domain.repository.RemoteUserRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindsLocalUsersRepository(
        impl: LocalUsersRepositoryImpl,
    ): LocalUsersRepository

    @Binds
    internal abstract fun bindsRemoteUserRepository(
        impl: RemoteUserRepositoryImpl,
    ): RemoteUserRepository

    @Binds
    internal abstract fun bindsDefaultUserRepository(
        impl: DefaultUserRepositoryImpl,
    ): DefaultUserRepository

}