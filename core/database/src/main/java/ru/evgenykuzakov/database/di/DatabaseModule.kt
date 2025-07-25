package ru.evgenykuzakov.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.evgenykuzakov.database.UsersRoomDatabase

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    fun provideUsersDatabase(
        @ApplicationContext context: Context,
    ): UsersRoomDatabase = Room.databaseBuilder(
        context,
        UsersRoomDatabase::class.java,
        "users_db"
    ).build()

}