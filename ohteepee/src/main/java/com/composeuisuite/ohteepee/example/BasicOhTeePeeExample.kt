package com.composeuisuite.ohteepee.example

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.composeuisuite.ohteepee.OhTeePeeDefaults
import com.composeuisuite.ohteepee.OhTeePeeInput
import com.composeuisuite.ohteepee.configuration.OhTeePeeCellBackground

@Composable
internal fun BasicOhTeePeeExample() {
    var otpValue: String by remember { mutableStateOf("12") }
    val defaultConfig = OhTeePeeDefaults.cellConfiguration(
        borderColor = Color.LightGray,
        borderWidth = 1.dp,
        cellBackground = OhTeePeeCellBackground.Solid(Color.White),
        shape = RoundedCornerShape(16.dp),
        textStyle = TextStyle(
            color = Color.Black,
        ),
    )

    val keyboardController = LocalSoftwareKeyboardController.current

    OhTeePeeInput(
        value = otpValue,
        onValueChange = { newValue, isValid ->
            otpValue = newValue
            if (isValid) {
                // Validate the value here...
            }
        },
        /* when the value is 1111, all cells will use errorCellConfig */
        isValueInvalid = otpValue == "1111",
        configurations = OhTeePeeDefaults.inputConfiguration(
            cellsCount = 4,
            emptyCellConfig = defaultConfig,
            filledCellConfig = defaultConfig,
            activeCellConfig = defaultConfig.copy(
                borderColor = Color.Black,
                borderWidth = 2.dp,
            ),
            errorCellConfig = defaultConfig.copy(
                borderColor = Color.Red,
                borderWidth = 2.dp,
            ),
            placeHolder = "-",
            cellModifier = Modifier.size(48.dp),
        ),
        imeAction = ImeAction.Done,
        callbackOnNext = {},
        callbackOnDone = {
            keyboardController?.hide()
        },
        callbackOnPrevious = {},
        callbackOnSearch = {},
        callbackOnSend = {},
        callbackOnGo = {},
        keyboardController = keyboardController,
    )
}

@Preview
@Composable
private fun BasicOhTeePeePreview() {
    MaterialTheme {
        BasicOhTeePeeExample()
    }
}
