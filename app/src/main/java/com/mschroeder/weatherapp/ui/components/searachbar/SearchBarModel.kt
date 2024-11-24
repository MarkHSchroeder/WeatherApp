package com.mschroeder.weatherapp.ui.components.searachbar

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

data class SearchBarModel(var placeholder: Int) : ViewModel() {
    val text = mutableStateOf("")
}