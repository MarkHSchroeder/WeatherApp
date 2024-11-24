package com.mschroeder.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Typography
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.mschroeder.weatherapp.ui.WeatherNavigation
import com.mschroeder.weatherapp.ui.WeatherScreens
import com.mschroeder.weatherapp.ui.components.searachbar.SearchBar
import com.mschroeder.weatherapp.ui.theme.poppinsFontFamily
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val mainViewModel = viewModel<MainViewModel>()
            val navController = rememberNavController()
            Surface(color = colorResource(id = R.color.backgroundColor)) {
                MaterialTheme(
                    typography = Typography(defaultFontFamily = poppinsFontFamily)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                horizontal = dimensionResource(id = R.dimen.pageHPadding),
                                vertical = dimensionResource(id = R.dimen.pageVPadding)
                            )
                    ) {
                        SearchBar(model = mainViewModel.editTextModel,
                            onSearch = { text ->
                                navController.navigate(WeatherScreens.SearchResultsScreen.route(text)) {
                                    popUpTo(WeatherScreens.HomeScreen.paramRoute()) {
                                        inclusive = false
                                    }
                                }
                            })
                        Scaffold(
                            modifier = Modifier.fillMaxSize()
                        ) { paddingValues ->
                            WeatherNavigation(
                                modifier = Modifier.padding(paddingValues),
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}
