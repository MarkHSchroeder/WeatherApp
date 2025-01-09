package com.mschroeder.weatherapp.ui.components.searchresult

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.mschroeder.weatherapp.R
import com.mschroeder.weatherapp.data.Condition
import com.mschroeder.weatherapp.data.CurrentWeather
import com.mschroeder.weatherapp.data.Location
import com.mschroeder.weatherapp.data.WeatherResponse

@Composable
fun SearchResult(model: WeatherResponse, onSearchItemClicked: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()
        .height(IntrinsicSize.Min)
        .clip(RoundedCornerShape(dimensionResource(id = R.dimen.boxCorners)))
        .background(colorResource(id = R.color.itemBackgroundColor))
        .clickable {
            onSearchItemClicked()
        },
        contentAlignment = Alignment.Center) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(all = dimensionResource(id = R.dimen.searchBoxInnerPadding)),
            verticalAlignment = Alignment.CenterVertically) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    model.location.name,
                    color = colorResource(R.color.textColor),
                    style = TextStyle(
                        fontSize = dimensionResource(id = R.dimen.searchCityTextSize).value.sp,
                        //lineHeight = dimensionResource(id = R.dimen.searchCityTextSize).value.sp,
                    ),
                    fontWeight = FontWeight.W600
                )
                // This makes for a degrees sign larger than the figma,
                // but matches the text size and prevents guesswork trying to line up the drawables
                Text(
                    stringResource(id = R.string.degree, model.current.temp_f.toInt()),
                    color = colorResource(R.color.textColor),
                    style = TextStyle(
                        fontSize = dimensionResource(id = R.dimen.searchTempTextSize).value.sp,
                        lineHeight = dimensionResource(id = R.dimen.noCityTextSize).value.sp,
                    ),
                    fontWeight = FontWeight.W500
                )
            }
            Spacer(modifier = Modifier.weight(1.0f))
            Image(
                painter = rememberAsyncImagePainter("https://${model.current.condition.icon}"),
                contentDescription = model.current.condition.text,
                modifier = Modifier.fillMaxHeight(),
                contentScale = ContentScale.FillHeight
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchResultPreview() {
    SearchResult(model = WeatherResponse(
        location = Location("City Name"),
        current = CurrentWeather(
            temp_f = 72.4f,
            condition = Condition(text = "Sunny", icon = "Sunny Icon"),
            humidity = 50,
            uv = 2.5f,
            feelslike_f = 75.6f
        )
    ),
        onSearchItemClicked = {})
}