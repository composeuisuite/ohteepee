package com.composeuisuite.ohteepee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.composeuisuite.ohteepee.configuration.OhTeePeeDefaults
import com.composeuisuite.ohteepee.ui.theme.OtpFieldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OtpFieldTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Sample1(modifier = Modifier.weight(1f))

                    Sample2(modifier = Modifier.weight(1f))

                    Sample3(modifier = Modifier.weight(1f))

                    Sample4(modifier = Modifier.weight(1f))

                    Sample5(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun Sample1(
    modifier: Modifier = Modifier
) {
    var otpValue: String by remember { mutableStateOf("") }
    val defaultConfig = OhTeePeeDefaults.singleCellConfiguration(
        borderColor = Color.LightGray,
        borderWidth = 2.dp
    )

    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OhTeePee(
            value = otpValue,
            cellsCount = 4,
            onValueChange = {
                otpValue = it
                println("MainActivity: $it")
            },
            isErrorOccurred = otpValue == "111111",
            cellConfigurations = OhTeePeeDefaults.cellConfigurations(
                cursorColor = Color(0xFF8C9EFF),
                emptyCellConfig = defaultConfig,
                filledCellConfig = defaultConfig.copy(
                    borderColor = Color(0xFF8C9EFF)
                ),
                activeCellConfig = defaultConfig.copy(
                    borderColor = Color(0xFF8C9EFF)
                ),
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .width(40.dp)
                    .height(56.dp),
                elevation = 8.dp,
            ),
            placeHolder = "-"
        )
    }
}

@Composable
private fun Sample2(
    modifier: Modifier = Modifier
) {
    val backgroundColor = Color(0xFFFF9E2A)
    var otpValue: String by remember { mutableStateOf("") }
    val defaultConfig = OhTeePeeDefaults.singleCellConfiguration(
        borderColor = Color.Transparent,
        borderWidth = 0.dp,
        backgroundColor = backgroundColor.copy(alpha = 0.6f),
        textStyle = TextStyle(
            color = Color.Black
        ),
        shape = RoundedCornerShape(24.dp)
    )

    Row(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OhTeePee(
            value = otpValue,
            cellsCount = 4,
            onValueChange = {
                otpValue = it
            },
            isErrorOccurred = otpValue == "111111",
            cellConfigurations = OhTeePeeDefaults.cellConfigurations(
                emptyCellConfig = defaultConfig,
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(48.dp),
                filledCellConfig = defaultConfig.copy(
                    backgroundColor = Color.White
                ),
                activeCellConfig = defaultConfig.copy(
                    backgroundColor = Color.White
                ),
                errorCellConfig = defaultConfig
            ),
            placeHolder = "-"
        )
    }
}

@Composable
private fun Sample3(
    modifier: Modifier = Modifier
) {
    var otpValue: String by remember { mutableStateOf("") }
    val transparentConfig = OhTeePeeDefaults.singleCellConfiguration(
        borderColor = Color(0xFFB388FF),
        borderWidth = 1.dp,
        shape = CircleShape,
        backgroundColor = Color(0xFF2F303B),
        textStyle = TextStyle(
            color = Color.White
        )
    )

    Row(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF3D3D49))
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OhTeePee(
            value = otpValue,
            cellsCount = 6,
            onValueChange = {
                otpValue = it
            },
            isErrorOccurred = otpValue == "111111",
            cellConfigurations = OhTeePeeDefaults.cellConfigurations(
                emptyCellConfig = transparentConfig,
                modifier = Modifier
                    .padding(horizontal = 2.dp)
                    .size(48.dp),
                filledCellConfig = transparentConfig,
                activeCellConfig = transparentConfig.copy(
                    borderColor = Color(0xFFEA80FC),
                    borderWidth = 1.dp
                ),
                errorCellConfig = transparentConfig
            ),
            obscureText = "•"
        )
    }
}

@Composable
private fun Sample4(
    modifier: Modifier = Modifier
) {
    var otpValue: String by remember { mutableStateOf("") }
    val transparentConfig = OhTeePeeDefaults.singleCellConfiguration(
        borderColor = Color.Transparent,
        borderWidth = 0.dp,
        shape = RoundedCornerShape(0.dp)
    )

    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OhTeePee(
            value = otpValue,
            modifier = Modifier.drawBehind {
                val strokeWidth = 1f * density
                val y = size.height

                drawLine(
                    color = Color.LightGray,
                    strokeWidth = strokeWidth,
                    start = Offset(0f, y),
                    end = Offset(size.width, y)
                )
            },
            cellsCount = 4,
            onValueChange = {
                otpValue = it
            },
            isErrorOccurred = otpValue == "111111",
            cellConfigurations = OhTeePeeDefaults.cellConfigurations(
                emptyCellConfig = transparentConfig,
                modifier = Modifier
                    .size(48.dp),
                filledCellConfig = transparentConfig,
                activeCellConfig = transparentConfig,
                errorCellConfig = transparentConfig
            ),
            obscureText = "•",
            placeHolder = "-"
        )
    }
}

@Composable
private fun Sample5(
    modifier: Modifier = Modifier
) {
    var otpValue: String by remember { mutableStateOf("") }
    val defaultConfig = OhTeePeeDefaults.singleCellConfiguration(
        borderColor = Color.LightGray,
        borderWidth = 1.dp,
        shape = RoundedCornerShape(16.dp),
        textStyle = TextStyle(
            color = Color(0xFF000000)
        )
    )

    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OhTeePee(
            modifier = Modifier
                .padding(8.dp),
            value = otpValue,
            cellsCount = 6,
            onValueChange = {
                otpValue = it
            },
            isErrorOccurred = otpValue == "111111",
            cellConfigurations = OhTeePeeDefaults.cellConfigurations(
                emptyCellConfig = defaultConfig,
                modifier = Modifier
                    .padding(horizontal = 1.dp)
                    .size(48.dp),
                filledCellConfig = defaultConfig,
                activeCellConfig = defaultConfig.copy(
                    borderWidth = 2.dp,
                    borderColor = Color.Black
                ),
                errorCellConfig = defaultConfig.copy(
                    borderWidth = 2.dp,
                    borderColor = Color.Red
                ),
            ),
            placeHolder = " "
        )
    }
}