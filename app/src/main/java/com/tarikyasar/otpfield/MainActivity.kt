package com.tarikyasar.otpfield

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tarikyasar.composeotpfield.OtpXComposable
import com.tarikyasar.composeotpfield.configuration.OtpXDefaults
import com.tarikyasar.otpfield.ui.theme.OtpFieldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var otpValue: String by remember { mutableStateOf("") }
            OtpFieldTheme {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    OtpXComposable(
                        value = otpValue,
                        cellsCount = 6,
                        onValueChange = {
                            otpValue = it
                            println(it)
                        },
                        isErrorOccurred = otpValue == "111111",
                        cellConfigurations = OtpXDefaults.cellConfigurations(
                            emptyCellConfig = OtpXDefaults.singleCellConfiguration(),
                            errorCellConfig = OtpXDefaults.singleCellConfiguration(borderColor = Color.Red),
                            activeCellConfig = OtpXDefaults.singleCellConfiguration(borderColor = Color.Magenta),
                            modifier = Modifier.padding(2.dp),
                            elevation = 8.dp
                        )
                    )
                }
            }
        }
    }
}