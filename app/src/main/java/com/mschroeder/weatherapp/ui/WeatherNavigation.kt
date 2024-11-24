package com.mschroeder.weatherapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mschroeder.weatherapp.ui.screens.HomeScreen
import com.mschroeder.weatherapp.ui.screens.SearchScreen
import com.mschroeder.weatherapp.utils.SharedPrefs

@Composable
fun WeatherNavigation(
    modifier: Modifier,
    navController: NavHostController
) {
    val context = LocalContext.current
    NavHost(
        navController = navController,
        startDestination = WeatherScreens.HomeScreen.route,
        modifier = modifier
    ) {
        composable(route = WeatherScreens.HomeScreen.paramRoute() ) {
            val city = SharedPrefs.loadCity(context)
            HomeScreen(city = city ?: "", viewModel = hiltViewModel())
        }
        composable(route = WeatherScreens.SearchResultsScreen.paramRoute(),
            arguments = listOf(navArgument(WeatherScreens.SearchResultsScreen.p1) { type = NavType.StringType})
        ) { entry ->
            val city = entry.arguments?.getString(WeatherScreens.SearchResultsScreen.p1)?: ""
            SearchScreen(city = city,
                viewModel = hiltViewModel(),
                onSearchItemClicked = {
                    SharedPrefs.saveCity(context, city)
                    navController.navigate(WeatherScreens.HomeScreen.route()) {
                        popUpTo(WeatherScreens.HomeScreen.paramRoute()) {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                })
        }
    }
}