package com.tarikyasar.otpfield

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
            val otpValue = remember { mutableStateOf("").value }
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
                            println(it)
                        },
                        cellConfigurations = OtpXDefaults.cellConfigurations(
                            emptyCellConfig = OtpXDefaults.singleCellConfiguration(
                                elevation = 8.dp
                            ),
                            modifier = Modifier.padding(2.dp)
                        )
                    )
                }
            }
        }
    }
}