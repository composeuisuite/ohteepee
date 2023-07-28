package com.composeuisuite.ohteepee.configuration

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object OhTeePeeDefaults {

    val BORDER_WIDTH = 1.dp

    @Composable
    fun singleCellConfiguration(
        shape: Shape = MaterialTheme.shapes.medium,
        backgroundColor: Color = MaterialTheme.colors.surface,
        borderColor: Color = MaterialTheme.colors.primary,
        borderWidth: Dp = BORDER_WIDTH,
        textStyle: TextStyle = TextStyle(),
    ) = SingleCellConfiguration(
        shape = shape,
        backgroundColor = backgroundColor,
        borderColor = borderColor,
        borderWidth = borderWidth,
        textStyle = textStyle
    )

    @Composable
    fun cellConfigurations(
        emptyCellConfig: SingleCellConfiguration,
        filledCellConfig: SingleCellConfiguration = emptyCellConfig,
        activeCellConfig: SingleCellConfiguration = emptyCellConfig.copy(
            borderColor = MaterialTheme.colors.primary
        ),
        errorCellConfig: SingleCellConfiguration = emptyCellConfig.copy(
            borderColor = MaterialTheme.colors.error
        ),
        modifier: Modifier = Modifier,
        elevation: Dp = 0.dp,
        cursorColor: Color = Color.Transparent,
        enableBottomLine: Boolean = false
    ) = CellConfigurations(
        modifier = modifier,
        elevation = elevation,
        activeCellConfig = activeCellConfig,
        errorCellConfig = errorCellConfig,
        emptyCellConfig = emptyCellConfig,
        filledCellConfig = filledCellConfig,
        cursorColor = cursorColor,
        enableBottomLine = enableBottomLine
    )
}