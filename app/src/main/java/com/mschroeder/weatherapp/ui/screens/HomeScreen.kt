package com.mschroeder.weatherapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.mschroeder.weatherapp.R
import com.mschroeder.weatherapp.data.WeatherViewModel
import com.mschroeder.weatherapp.ui.components.errordialog.ErrorDialog

@Composable
fun HomeScreen(city: String, viewModel: WeatherViewModel) {
    val weatherData by viewModel.weatherData.observeAsState()
    val loading by viewModel.loading.observeAsState(false)
    val error by viewModel.error.observeAsState(null)

    LaunchedEffect(Unit) {
        // Fetch city data
        viewModel.getWeather(city)
    }

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(1.0f))

        if (city.isEmpty()) {
            Text(text = stringResource(id = R.string.noCitySelected),
                color = colorResource(R.color.textColor),
                fontSize = dimensionResource(id = R.dimen.noCityTextSize).value.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.W600)
            Text(text = stringResource(id = R.string.pleaseSearch),
                color = colorResource(R.color.textColor),
                fontSize = dimensionResource(id = R.dimen.smallTextSize).value.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.W600)
        } else if (loading) {
            CircularProgressIndicator()
        } else if (error != null) {
            ErrorDialog(error) { viewModel.clearError() }
        } else {
            weatherData?.let { cityData ->
                Image(painter = rememberAsyncImagePainter("https://${cityData.current.condition.icon}"),
                    contentDescription = cityData.current.condition.text,
                    modifier = Modifier.width(dimensionResource(id = R.dimen.homeWeatherIconSize)),
                    contentScale = ContentScale.FillWidth
                )
                Box(modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = cityData.location.name,
                            color = colorResource(R.color.textColor),
                            style = TextStyle(
                                fontSize = dimensionResource(id = R.dimen.noCityTextSize).value.sp,
                                lineHeight = dimensionResource(id = R.dimen.noCityTextSize).value.sp,
                            ),
                            fontWeight = FontWeight.W600,
                            modifier = Modifier.padding(
                                end = dimensionResource(id = R.dimen.locationIconPadding)
                            )
                        )
                        Image(painter = painterResource(R.drawable.ic_location),
                            contentDescription = "", // not important enough for content description
                            modifier = Modifier.width(dimensionResource(id = R.dimen.locationIconSize))
                        )
                    }
                }
                // This makes for a degrees sign larger than the figma,
                // but matches the text size and prevents guesswork trying to line up the drawables
                Text(text = stringResource(id = R.string.degree, cityData.current.temp_f.toInt()),
                    color = colorResource(R.color.textColor),
                    style = TextStyle(
                        fontSize = dimensionResource(id = R.dimen.tempTextSize).value.sp,
                        lineHeight = dimensionResource(id = R.dimen.tempTextSize).value.sp,
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.W500
                )

                Box(modifier = Modifier.width(dimensionResource(id = R.dimen.weatherBoxWidth))
                        .padding(top = dimensionResource(id = R.dimen.weatherBoxPadding))
                        .clip(RoundedCornerShape(dimensionResource(id = R.dimen.boxCorners))),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(colorResource(id = R.color.itemBackgroundColor))
                            .padding(all = dimensionResource(id = R.dimen.weatherBoxInnerPadding))
                    ) {
                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(id = R.string.humidity),
                                modifier = Modifier.height(
                                    dimensionResource(id = R.dimen.weatherInfoHeaderHeight)),
                                color = colorResource(R.color.contentColor),
                                fontSize = dimensionResource(id = R.dimen.dataTextSize).value.sp,
                                fontWeight = FontWeight.W500
                            )
                            Text(
                                text = stringResource(id = R.string.percent, cityData.current.humidity),
                                modifier = Modifier.height(
                                    dimensionResource(id = R.dimen.weatherInfoDataHeight)),
                                color = colorResource(R.color.dataColor),
                                fontSize = dimensionResource(id = R.dimen.smallTextSize).value.sp,
                                fontWeight = FontWeight.W500
                            )
                        }
                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(id = R.string.uv),
                                modifier = Modifier.height(
                                    dimensionResource(id = R.dimen.weatherInfoHeaderHeight)),
                                color = colorResource(R.color.contentColor),
                                fontSize = dimensionResource(id = R.dimen.dataTextSize).value.sp,
                                fontWeight = FontWeight.W500
                            )
                            Text(
                                text = cityData.current.uv.toInt().toString(),
                                modifier = Modifier.height(
                                    dimensionResource(id = R.dimen.weatherInfoDataHeight)),
                                color = colorResource(R.color.dataColor),
                                fontSize = dimensionResource(id = R.dimen.smallTextSize).value.sp,
                                fontWeight = FontWeight.W500
                            )
                        }
                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(id = R.string.feels),
                                modifier = Modifier.height(
                                    dimensionResource(id = R.dimen.weatherFeelsHeaderHeight)),
                                color = colorResource(R.color.contentColor),
                                fontSize = dimensionResource(id = R.dimen.feelsTextSize).value.sp,
                                fontWeight = FontWeight.W500
                            )
                            Text(
                                text = stringResource(id = R.string.degree, cityData.current.feelslike_f.toInt()),
                                modifier = Modifier.height(
                                    dimensionResource(id = R.dimen.weatherInfoDataHeight)),
                                color = colorResource(R.color.dataColor),
                                fontSize = dimensionResource(id = R.dimen.smallTextSize).value.sp,
                                fontWeight = FontWeight.W500
                            )
                        }
                    }
                }
            } ?: run {
                Text(stringResource(id = R.string.noData))
            }
        }
        Spacer(modifier = Modifier.weight(1.0f))
    }
}