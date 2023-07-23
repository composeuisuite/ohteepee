package com.tarikyasar.composeotpfield.configuration

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

interface SingleCellConfiguration {
    val shape: Shape

    val backgroundColor: Color

    val borderColor: Color

    val borderWidth: Dp

    val elevation: Dp

    val textStyle: TextStyle
}