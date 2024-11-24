package com.mschroeder.weatherapp.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

object SharedPrefs {
    private const val PREFS_NAME = "WeatherPrefs"
    private const val CITY_KEY = "CityKey"

    fun saveCity(context: Context, city: String) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(CITY_KEY, city)
        editor.apply()
    }

     fun loadCity(context: Context): String? {
         val sharedPreferences = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
         return sharedPreferences.getString(CITY_KEY, null)
     }
}