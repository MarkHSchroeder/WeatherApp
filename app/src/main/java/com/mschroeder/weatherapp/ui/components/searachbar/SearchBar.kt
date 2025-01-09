package com.mschroeder.weatherapp.ui.components.searachbar

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.mschroeder.weatherapp.R

@Composable
fun SearchBar(model: SearchBarModel,
              modifier: Modifier = Modifier,
              onSearch: (String) -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = model.text.value,
        onValueChange = { model.text.value = it },
        textStyle = TextStyle.Default.copy(
            fontSize = dimensionResource(id = R.dimen.smallTextSize).value.sp,
            color = colorResource(id = R.color.textColor)
        ),
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colorResource(id = R.color.itemBackgroundColor),
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.boxCorners))
            )
            .height(dimensionResource(id = R.dimen.searchHeight)),
        interactionSource = interactionSource,
        enabled = true,
        singleLine = true,
        colors = TextFieldDefaults.colors(
            cursorColor = colorResource(R.color.textColor),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(onSearch = {
            if (model.text.value.isNotEmpty()) {
                onSearch(model.text.value)
                keyboardController?.hide()
            } // Let it silently fail if nothing has been entered
        }),
        placeholder = {
            Text(
                text = stringResource(id = model.placeholder),
                style = TextStyle(
                    fontSize = dimensionResource(id = R.dimen.smallTextSize).value.sp,
                    color = colorResource(id = R.color.contentColor),
                    fontWeight = FontWeight.W400
                )
            )
        },
        trailingIcon = {
            // We could use Icons.Filled.Search here, but using the figma icon for accuracy
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "", // Not important for content description
                tint = colorResource(id = R.color.contentColor)
            )
        }
    )
}