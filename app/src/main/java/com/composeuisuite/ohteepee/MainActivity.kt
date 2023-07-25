package com.composeuisuite.ohteepee

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.composeuisuite.ohteepee.configuration.OhTeePeeDefaults
import com.composeuisuite.ohteepee.ui.theme.OtpFieldTheme

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
                        .background(Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OhTeePee(
                        value = otpValue,
                        cellsCount = 6,
                        onValueChange = {
                            otpValue = it
                            println("MainActivity: $it")
                        },
                        isErrorOccurred = otpValue == "111111",
                        cellConfigurations = OhTeePeeDefaults.cellConfigurations(
                            emptyCellConfig = OhTeePeeDefaults.singleCellConfiguration(
                                borderColor = Color.Black,
                                borderWidth = 2.dp
                            ),
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .weight(1f),
                            elevation = 8.dp,
                        ),
                        obscureText = "*",
                        placeHolder = "-"
                    )
                }
            }
        }
    }
}