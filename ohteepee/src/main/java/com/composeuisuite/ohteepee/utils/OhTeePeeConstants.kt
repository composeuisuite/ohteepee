package com.composeuisuite.ohteepee.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import com.composeuisuite.ohteepee.configuration.OhTeePeeDefaults
import com.composeuisuite.ohteepee.configuration.SingleCellConfiguration

object OhTeePeeConstants {
    val ACTIVE_CELL_CONFIG: SingleCellConfiguration
        @Composable get() = OhTeePeeDefaults.singleCellConfiguration(
            textStyle = TextStyle()
        )
}