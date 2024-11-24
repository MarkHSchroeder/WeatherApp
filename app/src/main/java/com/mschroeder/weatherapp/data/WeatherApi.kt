package com.mschroeder.weatherapp.data

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/v1/current.json")
    suspend fun getCurrentWeather(
        @Query("key") key: String,
        @Query("q") city: String
    ): WeatherResponse
}