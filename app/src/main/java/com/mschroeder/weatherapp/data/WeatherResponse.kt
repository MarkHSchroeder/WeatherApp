package com.mschroeder.weatherapp.data

data class WeatherResponse(
    val location: Location,
    val current: CurrentWeather
)

data class Location(
    val name: String
)

data class CurrentWeather(
    val temp_f: Float,
    val condition: Condition,
    val humidity: Int,
    val uv: Float,
    val feelslike_f: Float
)

data class Condition(
    val text: String,
    val icon: String
)
