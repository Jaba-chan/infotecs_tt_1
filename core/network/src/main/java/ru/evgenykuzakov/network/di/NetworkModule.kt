package ru.evgenykuzakov.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.evgenykuzakov.network.MockErrorInterceptor
import ru.evgenykuzakov.network.RandomUserApi
import javax.inject.Singleton

private const val BASE_URL = "https://randomuser.me/"

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    private const val NEED_ERROR_TEST = false

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient{
        return OkHttpClient.Builder().apply {
            if (NEED_ERROR_TEST) addInterceptor(MockErrorInterceptor())
        }.build()
    }

    @Provides
    @Singleton
    fun provideRandomUserApi(
        client: OkHttpClient,
    ): RandomUserApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RandomUserApi::class.java)
    }

}