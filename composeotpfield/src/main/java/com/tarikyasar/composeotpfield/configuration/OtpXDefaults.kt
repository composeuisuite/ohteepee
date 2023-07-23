package com.tarikyasar.composeotpfield.configuration

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tarikyasar.composeotpfield.utils.OtpXConstants.ACTIVE_CELL_CONFIG

object OtpXDefaults {

    val BORDER_WIDTH = 1.dp

    @Composable
    fun singleCellConfiguration(
        shape: Shape = MaterialTheme.shapes.medium,
        backgroundColor: Color = MaterialTheme.colors.surface,
        borderColor: Color = MaterialTheme.colors.primary,
        borderWidth: Dp = BORDER_WIDTH,
        textStyle: TextStyle = TextStyle(),
        elevation: Dp = 0.dp
    ) = object : SingleCellConfiguration {
        override val shape: Shape = shape
        override val backgroundColor: Color = backgroundColor
        override val borderColor: Color = borderColor
        override val borderWidth: Dp = borderWidth
        override val elevation: Dp = elevation
        override val textStyle: TextStyle = textStyle
    }

    @Composable
    fun cellConfigurations(
        width: Dp = 64.dp,
        height: Dp = 64.dp,
        emptyCellConfig: SingleCellConfiguration = ACTIVE_CELL_CONFIG,
        filledCellConfig: SingleCellConfiguration = ACTIVE_CELL_CONFIG,
        activeCellConfig: SingleCellConfiguration = ACTIVE_CELL_CONFIG,
        errorCellConfig: SingleCellConfiguration = ACTIVE_CELL_CONFIG,
        modifier: Modifier = Modifier
    ) = object : CellConfigurations {
        override val modifier: Modifier = modifier
        override val width: Dp = width
        override val height: Dp = height
        override val activeCellConfig: SingleCellConfiguration = activeCellConfig
        override val errorCellConfig: SingleCellConfiguration = errorCellConfig
        override val emptyCellConfig: SingleCellConfiguration = emptyCellConfig
        override val filledCellConfig: SingleCellConfiguration = filledCellConfig
    }
}