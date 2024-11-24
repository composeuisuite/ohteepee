package com.composeuisuite.ohteepee.configuration

import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.composeuisuite.ohteepee.utils.EMPTY

const val OH_TEE_PEE_DEFAULT_PLACE_HOLDER = ' '

/**
 * OhTeePeeConfigurations is a class that holds all the ui configurations for OhTeePeeInput.
 *
 * If you don't want to pass all the configurations, you can use [OhTeePeeConfigurations.withDefaults] to pass
 * only the configurations you want.
 *
 * @param cellModifier a [Modifier] for each cell.
 * @param elevation [Dp] applied for each cell.
 * @param cursorColor [Color] .
 * @param cellsCount [Int] number of cells to be shown.
 *
 * @param obscureText String when set, all the inputs will be visually replaces with this text.
 * It can be usefully when you have a Pin/Password View.
 *
 * Example when the [obscureText] = "`*`" the input will view "`****`"
 *
 * Note: it should be a single char.
 *
 * @param placeHolder String a string that will be used as a place holder for empty cells.
 * @param activeCellConfig [OhTeePeeCellConfiguration] cell ui configuration when it's focused.
 * @param errorCellConfig [OhTeePeeCellConfiguration] cell ui configuration when an error occurred.
 * @param emptyCellConfig [OhTeePeeCellConfiguration] cell ui configuration when it's empty & not focused.
 * @param filledCellConfig [OhTeePeeCellConfiguration] cell ui configuration when it's filled.
 * @param clearInputOnError when set to `true`, the input will be cleared and the focus will be reset to the first char
 * when an error occurred.
 * @param enableBottomLine when set to `true`, a bottom line will be drawn for each
 * cell instead of full shape using the other cell configurations.
 * @param errorAnimationConfig [OhTeePeeErrorAnimationConfig] animation config when an error occurred.
 * it will run only once when isValueInvalid is `true`.
 *
 * @see [OhTeePeeCellConfiguration.withDefaults]
 */
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
    val clearInputOnError: Boolean,
    val enableBottomLine: Boolean,
    val errorAnimationConfig: OhTeePeeErrorAnimationConfig?,
) {
    companion object {
        @Composable
        fun withDefaults(
            cellsCount: Int,
            emptyCellConfig: OhTeePeeCellConfiguration,
            filledCellConfig: OhTeePeeCellConfiguration = emptyCellConfig,
            activeCellConfig: OhTeePeeCellConfiguration = emptyCellConfig.copy(
                borderColor = MaterialTheme.colors.primary,
            ),
            errorCellConfig: OhTeePeeCellConfiguration = emptyCellConfig.copy(
                borderColor = MaterialTheme.colors.error,
            ),
            cellModifier: Modifier = Modifier.size(48.dp),
            elevation: Dp = 0.dp,
            cursorColor: Color = Color.Transparent,
            clearInputOnError: Boolean = true,
            enableBottomLine: Boolean = false,
            obscureText: String = String.EMPTY,
            placeHolder: String = OH_TEE_PEE_DEFAULT_PLACE_HOLDER.toString(),
            errorAnimationConfig: OhTeePeeErrorAnimationConfig? = OhTeePeeErrorAnimationConfig.Shake(),
        ) = OhTeePeeConfigurations(
            cellModifier = cellModifier,
            elevation = elevation,
            activeCellConfig = activeCellConfig,
            errorCellConfig = errorCellConfig,
            emptyCellConfig = emptyCellConfig,
            filledCellConfig = filledCellConfig,
            cursorColor = cursorColor,
            clearInputOnError = clearInputOnError,
            enableBottomLine = enableBottomLine,
            placeHolder = placeHolder,
            obscureText = obscureText,
            cellsCount = cellsCount,
            errorAnimationConfig = errorAnimationConfig,
        )
    }
}
