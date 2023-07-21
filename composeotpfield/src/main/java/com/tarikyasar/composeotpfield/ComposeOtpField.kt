package com.tarikyasar.composeotpfield

import androidx.compose.foundation.background
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun ComposeOtpField(
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onFocusChanged: ((isFocused: Boolean) -> Unit)? = null
) {
    var code by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }

    TextField(
        modifier = modifier
            .background(MaterialTheme.colors.surface)
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
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colors.primary,
            backgroundColor = MaterialTheme.colors.surface,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        textStyle = TextStyle(
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
    )
}