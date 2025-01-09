package com.mschroeder.weatherapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mschroeder.weatherapp.BuildConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    private val _weatherData = MutableLiveData<WeatherResponse>()
    val weatherData: LiveData<WeatherResponse> get() = _weatherData

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<Exception?>()
    val error: LiveData<Exception?> get() = _error

    fun getWeather(location: String) {
        if (location.isNotEmpty()) {
            viewModelScope.launch {
                _loading.value = true
                try {
                    val response = weatherRepository.getWeather(BuildConfig.WEATHER_API_KEY, location)
                    _weatherData.value = response
                } catch (e: Exception) {
                    _error.value = e
                } finally {
                    _loading.value = false
                }
            }
        } // Letting silently fail if no text is entered.
    }

    fun clearError() {
        _error.value = null
    }
}