package com.tarikyasar.composeotpfield

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.tarikyasar.composeotpfield.configuration.CellConfigurations

@Composable
fun OtpXCell(
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType,
    modifier: Modifier = Modifier,
    cellConfigurations: CellConfigurations,
    onFocusChanged: ((isFocused: Boolean) -> Unit)? = null
) {
    var code by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }
    val cellConfigurationState by remember(
        key1 = value,
        key2 = isFocused
    ) {
        mutableStateOf(cellConfigurations.emptyCellConfig)
    }

    Surface(
        modifier = modifier
            .width(cellConfigurations.width)
            .height(cellConfigurations.height)
            .border(
                border = BorderStroke(
                    width = cellConfigurationState.borderWidth,
                    color = cellConfigurationState.borderColor
                ),
                shape = cellConfigurationState.shape
            ),
        elevation = cellConfigurationState.elevation,
        shape = cellConfigurationState.shape,
    ) {
        TextField(
            modifier = Modifier
//            .absolutePadding(left = 2.dp, right = 1.dp)
                .onFocusEvent {
                    onFocusChanged?.invoke(it.isFocused)
                    isFocused = it.isFocused
                },
            singleLine = true,
            value = code,
            onValueChange = {
                if (it.length <= 1 && it != " ") {
                    code = it
                    onValueChange(code)
                }
            },
            keyboardActions = KeyboardActions(
                onNext = {},
                onDone = {}
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = ImeAction.Next
            ),
            colors = TextFieldDefaults.textFieldColors(
                textColor = cellConfigurationState.textStyle.color,
                backgroundColor = cellConfigurationState.backgroundColor,
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Transparent,
            ),
            textStyle = cellConfigurationState.textStyle.copy(textAlign = TextAlign.Center)
        )
    }
}