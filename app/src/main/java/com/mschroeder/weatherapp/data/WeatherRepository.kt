package com.mschroeder.weatherapp.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
    private val apiService: WeatherApi
) {

    suspend fun getWeather(apiKey: String, location: String): WeatherResponse {
        return apiService.getCurrentWeather(apiKey, location)
    }
}