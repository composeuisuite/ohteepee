package com.tarikyasar.composeotpfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.text.input.KeyboardType
import com.tarikyasar.composeotpfield.configuration.CellConfigurations
import com.tarikyasar.composeotpfield.configuration.OtpXDefaults
import com.tarikyasar.composeotpfield.utils.EMPTY
import com.tarikyasar.composeotpfield.utils.orElse

private const val NOT_ENTERED_CELL_INDICATOR = '∆'

@Composable
fun OtpXComposable(
    cellsCount: Int,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    cellConfigurations: CellConfigurations = OtpXDefaults.cellConfigurations(),
    isErrorOccurred: Boolean = false,
    obscureText: String = String.EMPTY,
    placeHolder: String = String.EMPTY,
    keyboardType: KeyboardType = KeyboardType.Number,
) {
    val otpValue = remember(value, isErrorOccurred) {
        val charList = CharArray(cellsCount) { index ->
            if (isErrorOccurred) {
                NOT_ENTERED_CELL_INDICATOR
            } else {
                value.getOrElse(index) { NOT_ENTERED_CELL_INDICATOR }
            }
        }
        mutableStateOf(charList).value
    }
    val focusRequester = remember { List(cellsCount) { FocusRequester() } }

    LaunchedEffect(Unit) {
        focusRequester.first().requestFocus()
    }

    LaunchedEffect(isErrorOccurred) {
        if (isErrorOccurred) focusRequester.first().requestFocus()
    }

    val transparentTextSelectionColors = TextSelectionColors(
        handleColor = Transparent,
        backgroundColor = Transparent
    )

    CompositionLocalProvider(LocalTextSelectionColors provides transparentTextSelectionColors) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            repeat(cellsCount) { index ->
                OtpXCell(
                    value = otpValue[index]
                        .toString()
                        .replace(NOT_ENTERED_CELL_INDICATOR.toString(), "")
                        .let {
                            if (it.isNotEmpty() && obscureText.isNotEmpty()) {
                                obscureText
                            } else {
                                it
                            }
                        }
                        .takeIf { it.isNotEmpty() }
                        .orElse(placeHolder),
                    isErrorOccurred = isErrorOccurred,
                    keyboardType = keyboardType,
                    modifier = cellConfigurations.modifier
                        .weight(1f)
                        .focusOrder(focusRequester = focusRequester[index]),
                    cellConfigurations = cellConfigurations,
                    onValueChange = {
                        val text = it.replace(placeHolder, String.EMPTY)
                            .replace(obscureText, String.EMPTY)

                        if (text.isBlank()) {
                            focusRequester[(index - 1).coerceIn(0, cellsCount - 1)].requestFocus()
                            otpValue.set(index = index, value = NOT_ENTERED_CELL_INDICATOR)
                        } else {
                            // todo: Find the first empty cell and focus it
                            focusRequester[(index + 1).coerceIn(0, cellsCount - 1)].requestFocus()
                            otpValue[index] = text.first()
                        }
                        onValueChange(otpValue.joinToString("").replace("-", String.EMPTY))
                    }
                )
            }
        }
    }
}