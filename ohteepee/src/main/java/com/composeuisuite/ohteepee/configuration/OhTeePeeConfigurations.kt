package com.composeuisuite.ohteepee.configuration

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.composeuisuite.ohteepee.utils.EMPTY

private const val DEFAULT_PLACE_HOLDER = " "

data class OhTeePeeConfigurations(
    val cellModifier: Modifier,
    val elevation: Dp,
    val cursorColor: Color,
    val cellsCount: Int,
    val obscureText: String,
    val placeHolder: String,
    val activeCellConfig: OhTeePeeCellConfiguration,
    val errorCellConfig: OhTeePeeCellConfiguration,
    val emptyCellConfig: OhTeePeeCellConfiguration,
    val filledCellConfig: OhTeePeeCellConfiguration,
    val enableBottomLine: Boolean
) {

    companion object {
        @Composable
        fun withDefaults(
            cellsCount: Int,
            emptyCellConfig: OhTeePeeCellConfiguration,
            filledCellConfig: OhTeePeeCellConfiguration = emptyCellConfig,
            activeCellConfig: OhTeePeeCellConfiguration = emptyCellConfig.copy(
                borderColor = MaterialTheme.colors.primary
            ),
            errorCellConfig: OhTeePeeCellConfiguration = emptyCellConfig.copy(
                borderColor = MaterialTheme.colors.error
            ),
            cellModifier: Modifier = Modifier
                .padding(horizontal = 2.dp)
                .size(48.dp),
            elevation: Dp = 0.dp,
            cursorColor: Color = Color.Transparent,
            enableBottomLine: Boolean = false,
            obscureText: String = String.EMPTY,
            placeHolder: String = DEFAULT_PLACE_HOLDER,
        ) = OhTeePeeConfigurations(
            cellModifier = cellModifier,
            elevation = elevation,
            activeCellConfig = activeCellConfig,
            errorCellConfig = errorCellConfig,
            emptyCellConfig = emptyCellConfig,
            filledCellConfig = filledCellConfig,
            cursorColor = cursorColor,
            enableBottomLine = enableBottomLine,
            placeHolder = placeHolder,
            obscureText = obscureText,
            cellsCount = cellsCount
        )
    }
}


