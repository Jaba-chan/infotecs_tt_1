package ru.evgenykuzakov.database.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.evgenykuzakov.database.UsersRoomDatabase
import ru.evgenykuzakov.database.dao.UsersDao

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {

    @Provides
    fun provideUserDao(
        database: UsersRoomDatabase
    ): UsersDao = database.usersDao()

}