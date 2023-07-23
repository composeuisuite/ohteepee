package com.tarikyasar.composeotpfield.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import com.tarikyasar.composeotpfield.configuration.OtpXDefaults
import com.tarikyasar.composeotpfield.configuration.SingleCellConfiguration

object OtpXConstants {
    val ACTIVE_CELL_CONFIG: SingleCellConfiguration
        @Composable get() = OtpXDefaults.singleCellConfiguration(
            textStyle = TextStyle()
        )
}