package com.mschroeder.weatherapp.ui.components.errordialog

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mschroeder.weatherapp.R
import java.net.SocketTimeoutException
import java.net.UnknownHostException

@Composable
fun ErrorDialog(error: Exception?, onOKClicked: () -> Unit) {
    AlertDialog(
        onDismissRequest = {  },
        title = { Text(text = stringResource(R.string.error)) },
        text = {
            val errorMessage = stringResource(id =
                when (error) {
                    is SocketTimeoutException, is UnknownHostException -> R.string.noNetwork
                    else -> R.string.invalidCity
                }
            )
            Text(errorMessage)
        },
        confirmButton = {
            TextButton(onClick = { onOKClicked() }) {
                Text(stringResource(id = R.string.ok))
            }
        }
    )
}