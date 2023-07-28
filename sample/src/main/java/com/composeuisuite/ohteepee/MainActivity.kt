package com.composeuisuite.ohteepee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.composeuisuite.ohteepee.configuration.OhTeePeeCellConfiguration
import com.composeuisuite.ohteepee.configuration.OhTeePeeConfigurations
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
                    Sample0(modifier = Modifier.weight(1f))

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
private fun Sample0(
    modifier: Modifier = Modifier
) {
    val backgroundColor = Color(0xFFFFFFFF)
    var otpValue: String by remember { mutableStateOf("") }
    val defaultConfig = OhTeePeeCellConfiguration.withDefaults(
        borderColor = Color.Gray,
        borderWidth = 2.dp,
        backgroundColor = backgroundColor.copy(alpha = 0.6f),
        textStyle = TextStyle(
            color = Color.Black
        )
    )

    Row(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OhTeePeeInput(
            value = otpValue,
            onValueChange = { newValue, isValid ->
                otpValue = newValue
                println("MainActivity: $newValue is valid $isValid")
            },
            isValueInvalid = otpValue == "111111",
            configurations = OhTeePeeConfigurations.withDefaults(
                placeHolder = "-",
                cellsCount = 6,
                emptyCellConfig = defaultConfig,
                cellModifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(48.dp),
                activeCellConfig = defaultConfig.copy(
                    borderColor = Color.Red
                ),
                enableBottomLine = true
            ),

        )
    }
}

@Composable
private fun Sample1(
    modifier: Modifier = Modifier
) {
    var otpValue: String by remember { mutableStateOf("") }
    val defaultConfig = OhTeePeeCellConfiguration.withDefaults(
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
        OhTeePeeInput(
            value = otpValue,

            onValueChange = { newValue, _ ->
                otpValue = newValue
            },
            isValueInvalid = otpValue == "111111",
            configurations = OhTeePeeConfigurations.withDefaults(
                cellsCount = 4,
                placeHolder = "-",
                cursorColor = Color(0xFF8C9EFF),
                emptyCellConfig = defaultConfig,
                filledCellConfig = defaultConfig.copy(
                    borderColor = Color(0xFF8C9EFF)
                ),
                activeCellConfig = defaultConfig.copy(
                    borderColor = Color(0xFF8C9EFF)
                ),
                cellModifier = Modifier
                    .padding(horizontal = 4.dp)
                    .width(40.dp)
                    .height(56.dp),
                elevation = 8.dp,
            ),
            autoFocusByDefault = false
        )
    }
}

@Composable
private fun Sample2(
    modifier: Modifier = Modifier
) {
    val backgroundColor = Color(0xFFFF9E2A)
    var otpValue: String by remember { mutableStateOf("") }
    val defaultConfig = OhTeePeeCellConfiguration.withDefaults(
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
        OhTeePeeInput(
            value = otpValue,
            onValueChange = { newValue, _ ->
                otpValue = newValue
            },
            isValueInvalid = otpValue == "111111",
            configurations = OhTeePeeConfigurations.withDefaults(
                emptyCellConfig = defaultConfig,
                cellsCount = 4,
                placeHolder = "-",
                cellModifier = Modifier
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
            autoFocusByDefault = false
        )
    }
}

@Composable
private fun Sample3(
    modifier: Modifier = Modifier
) {
    var otpValue: String by remember { mutableStateOf("") }
    val transparentConfig = OhTeePeeCellConfiguration.withDefaults(
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
        OhTeePeeInput(
            value = otpValue,
            onValueChange = { newValue, _ ->
                otpValue = newValue
            },
            isValueInvalid = otpValue == "111111",
            configurations = OhTeePeeConfigurations.withDefaults(
                emptyCellConfig = transparentConfig,
                cellModifier = Modifier
                    .padding(horizontal = 2.dp)
                    .size(48.dp),
                filledCellConfig = transparentConfig,
                activeCellConfig = transparentConfig.copy(
                    borderColor = Color(0xFFEA80FC),
                    borderWidth = 1.dp
                ),
                errorCellConfig = transparentConfig,
                cellsCount = 6,
                obscureText = "•",
            ),
            autoFocusByDefault = false
        )
    }
}

@Composable
private fun Sample4(
    modifier: Modifier = Modifier
) {
    var otpValue: String by remember { mutableStateOf("") }
    val transparentConfig = OhTeePeeCellConfiguration.withDefaults(
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
        OhTeePeeInput(
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

            onValueChange = { newValue, _ ->
                otpValue = newValue
            },
            isValueInvalid = otpValue == "111111",
            configurations = OhTeePeeConfigurations.withDefaults(
                emptyCellConfig = transparentConfig,
                cellModifier = Modifier
                    .size(48.dp),
                filledCellConfig = transparentConfig,
                activeCellConfig = transparentConfig,
                errorCellConfig = transparentConfig,
                cellsCount = 4,
                obscureText = "•",
                placeHolder = "-",
            ),
            autoFocusByDefault = false
        )
    }
}

@Composable
private fun Sample5(
    modifier: Modifier = Modifier
) {
    var otpValue: String by remember { mutableStateOf("") }
    val defaultConfig = OhTeePeeCellConfiguration.withDefaults(
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
        OhTeePeeInput(
            modifier = Modifier
                .padding(8.dp),
            value = otpValue,

            onValueChange = { newValue, _ ->
                otpValue = newValue
            },
            isValueInvalid = otpValue == "111111",
            configurations = OhTeePeeConfigurations.withDefaults(
                cellsCount = 6,
                emptyCellConfig = defaultConfig,
                cellModifier = Modifier
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
                placeHolder = " ",
            ),
            autoFocusByDefault = false
        )
    }
}