package com.composeuisuite.ohteepee.configuration

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class CellConfigurations(
    val modifier: Modifier,
    val elevation: Dp,
    val cursorColor: Color,
    val activeCellConfig: SingleCellConfiguration,
    val errorCellConfig: SingleCellConfiguration,
    val emptyCellConfig: SingleCellConfiguration,
    val filledCellConfig: SingleCellConfiguration,
    val enableBottomLine: Boolean
) {

    companion object {
        @Composable
        fun withDefaults(
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
}


