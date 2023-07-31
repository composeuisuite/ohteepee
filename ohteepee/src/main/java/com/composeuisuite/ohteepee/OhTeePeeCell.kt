package com.composeuisuite.ohteepee

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.composeuisuite.ohteepee.configuration.OhTeePeeConfigurations
import com.composeuisuite.ohteepee.utils.conditional

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun OhTeePeeCell(
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType,
    isCurrentCharAPlaceHolder: Boolean,
    configurations: OhTeePeeConfigurations,
    isErrorOccurred: Boolean,
    enabled: Boolean,
    modifier: Modifier = Modifier,
) {
    var isFocused by remember { mutableStateOf(false) }
    val cellConfiguration by remember(
        key1 = value,
        key2 = isFocused,
        key3 = isErrorOccurred
    ) {
        val config = when {
            isErrorOccurred -> configurations.errorCellConfig
            isFocused -> configurations.activeCellConfig
            value.isNotEmpty() && isCurrentCharAPlaceHolder.not() -> configurations.filledCellConfig
            else -> configurations.emptyCellConfig
        }
        mutableStateOf(config)
    }
    val textStyle by remember(cellConfiguration) {
        val style = if (isCurrentCharAPlaceHolder) {
            cellConfiguration.placeHolderTextStyle
        } else {
            cellConfiguration.textStyle
        }
        mutableStateOf(style.copy(textAlign = TextAlign.Center))
    }

    val textFieldValue = remember(value) {
        TextFieldValue(
            text = value,
            selection = TextRange(value.length),
        )
    }

    Surface(
        modifier = modifier
            .conditional(configurations.enableBottomLine) {
                drawBehind {
                    if (configurations.enableBottomLine) {
                        val y = size.height

                        drawLine(
                            color = cellConfiguration.borderColor,
                            start = Offset(0f, y),
                            end = Offset(size.width, y),
                            strokeWidth = cellConfiguration.borderWidth.toPx()
                        )
                    }
                }
            }
            .conditional(configurations.enableBottomLine.not()) {
                border(
                    border = BorderStroke(
                        width = cellConfiguration.borderWidth,
                        color = cellConfiguration.borderColor
                    ),
                    shape = cellConfiguration.shape
                )
            },
        elevation = configurations.elevation,
        shape = if (configurations.enableBottomLine) RoundedCornerShape(0.dp) else cellConfiguration.shape,
    ) {
        val interactionSource = remember { MutableInteractionSource() }
        BasicTextField(
            value = textFieldValue,
            onValueChange = {
                if (it.text == value) return@BasicTextField
                onValueChange(it.text)
            },
            textStyle = textStyle,
            modifier = Modifier
                .onFocusEvent { isFocused = it.isFocused }
                .background(cellConfiguration.backgroundColor),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {},
                onDone = {}
            ),
            singleLine = true,
            enabled = enabled,
            cursorBrush = SolidColor(configurations.cursorColor),
        ) { innerTextField ->
            TextFieldDefaults.TextFieldDecorationBox(
                value = value,
                visualTransformation = VisualTransformation.None,
                innerTextField = innerTextField,
                singleLine = true,
                enabled = enabled,
                interactionSource = interactionSource,
                contentPadding = PaddingValues(0.dp),
            )
        }
    }
}