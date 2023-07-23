package com.tarikyasar.composeotpfield.configuration

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

interface CellConfigurations {

    val modifier: Modifier

    val width: Dp

    val height: Dp

    val elevation: Dp

    val activeCellConfig: SingleCellConfiguration

    val errorCellConfig: SingleCellConfiguration

    val emptyCellConfig: SingleCellConfiguration

    val filledCellConfig: SingleCellConfiguration
}