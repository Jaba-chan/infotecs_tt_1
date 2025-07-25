package ru.evgenykuzakov.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.evgenykuzakov.network.model.ResultDto

interface RandomUserApi {

    @GET("/api")
    suspend fun getUsers(@Query("results") results: Int = 50): Response<ResultDto>

}