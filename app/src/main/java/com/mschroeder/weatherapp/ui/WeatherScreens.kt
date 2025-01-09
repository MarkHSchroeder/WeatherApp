package com.mschroeder.weatherapp.ui

sealed class WeatherScreens(val route: String, val p1: String) {
    data object HomeScreen: WeatherScreens("home", "")
    data object SearchResultsScreen: WeatherScreens("search/{p1}", "city")

    fun route(param: String = ""): String {
        return route.replace("{p1}", param)
    }

    fun paramRoute(): String {
        return route.replace("p1", p1)
    }

}