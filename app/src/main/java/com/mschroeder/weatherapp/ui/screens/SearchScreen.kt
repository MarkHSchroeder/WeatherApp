package com.mschroeder.weatherapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.mschroeder.weatherapp.R
import com.mschroeder.weatherapp.data.WeatherViewModel
import com.mschroeder.weatherapp.ui.components.errordialog.ErrorDialog
import com.mschroeder.weatherapp.ui.components.searchresult.SearchResult

@Composable
fun SearchScreen(city: String,
                 viewModel: WeatherViewModel,
                 onSearchItemClicked: () -> Unit) {
    val weatherData by viewModel.weatherData.observeAsState()
    val loading by viewModel.loading.observeAsState(false)
    val error by viewModel.error.observeAsState(null)

    LaunchedEffect(Unit) {
        // Fetch city data
        viewModel.getWeather(city)
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = dimensionResource(id = R.dimen.searchTopPadding)),
        horizontalAlignment = Alignment.CenterHorizontally) {
        if (loading) {
            Spacer(modifier = Modifier.weight(1.0f))
            CircularProgressIndicator()
            Spacer(modifier = Modifier.weight(1.0f))
        } else if (error != null) {
            ErrorDialog(error) { viewModel.clearError() }
        } else {
            weatherData?.let { cityData ->
                SearchResult(model = cityData,
                    onSearchItemClicked = onSearchItemClicked
                )
            }
        }
    }
}