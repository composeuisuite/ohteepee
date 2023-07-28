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
import com.composeuisuite.ohteepee.configuration.CellConfigurations
import com.composeuisuite.ohteepee.utils.conditional

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun OhTeePeeCell(
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType,
    isCurrentCharAPlaceHolder: Boolean,
    cellConfigurations: CellConfigurations,
    isErrorOccurred: Boolean,
    enabled: Boolean,
    modifier: Modifier = Modifier,
) {
    var isFocused by remember { mutableStateOf(false) }
    val cellConfigurationState by remember(
        key1 = value,
        key2 = isFocused,
        key3 = isErrorOccurred
    ) {
        val config = when {
            isErrorOccurred -> cellConfigurations.errorCellConfig
            isFocused -> cellConfigurations.activeCellConfig
            value.isNotEmpty() && isCurrentCharAPlaceHolder.not() -> cellConfigurations.filledCellConfig
            else -> cellConfigurations.emptyCellConfig
        }
        mutableStateOf(config)
    }

    val textFieldValue = remember(value) {
        TextFieldValue(
            text = value,
            selection = TextRange(value.length),
        )
    }

    Surface(
        modifier = modifier
            .conditional(cellConfigurations.enableBottomLine) {
                drawBehind {
                    if (cellConfigurations.enableBottomLine) {
                        val y = size.height

                        drawLine(
                            color = cellConfigurationState.borderColor,
                            start = Offset(0f, y),
                            end = Offset(size.width, y),
                            strokeWidth = cellConfigurationState.borderWidth.toPx()
                        )
                    }
                }
            }
            .conditional(cellConfigurations.enableBottomLine.not()) {
                border(
                    border = BorderStroke(
                        width = cellConfigurationState.borderWidth,
                        color = cellConfigurationState.borderColor
                    ),
                    shape = cellConfigurationState.shape
                )
            },
        elevation = cellConfigurations.elevation,
        shape = if (cellConfigurations.enableBottomLine) RoundedCornerShape(0.dp) else cellConfigurationState.shape,
    ) {
        val interactionSource = remember { MutableInteractionSource() }
        BasicTextField(
            value = textFieldValue,
            onValueChange = {
                if (it.text == value) return@BasicTextField
                onValueChange(it.text)
            },
            textStyle = cellConfigurationState.textStyle.copy(textAlign = TextAlign.Center),
            modifier = Modifier
                .onFocusEvent { isFocused = it.isFocused }
                .background(cellConfigurationState.backgroundColor),
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
            cursorBrush = SolidColor(cellConfigurations.cursorColor),
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