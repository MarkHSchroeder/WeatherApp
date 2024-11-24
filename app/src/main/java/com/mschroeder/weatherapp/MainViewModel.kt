package com.mschroeder.weatherapp

import androidx.lifecycle.ViewModel
import com.mschroeder.weatherapp.ui.components.searachbar.SearchBarModel

class MainViewModel : ViewModel() {
    val editTextModel = SearchBarModel(placeholder = R.string.searchPlaceholder)
}