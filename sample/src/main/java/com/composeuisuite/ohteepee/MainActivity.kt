package com.composeuisuite.ohteepee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composeuisuite.ohteepee.configuration.OhTeePeeCellConfiguration
import com.composeuisuite.ohteepee.configuration.OhTeePeeConfigurations
import com.composeuisuite.ohteepee.ui.theme.OtpFieldTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val pagerState = rememberPagerState(initialPage = 0)

            OtpFieldTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    HorizontalPager(
                        pageCount = 3,
                        modifier = Modifier.fillMaxSize(),
                        state = pagerState
                    ) {
                        when (it) {
                            0 -> Sample0()
                            1 -> Sample1()
                            2 -> Sample2()
                        }
                    }

                    Text(
                        text = "Sample No: ${pagerState.currentPage}",
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(8.dp)
                            .background(color = Color.Black.copy(0.6f), RoundedCornerShape(8.dp))
                            .padding(8.dp),
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
private fun Sample0(
    modifier: Modifier = Modifier
) {
    val backgroundColor = Color(0xFF4F4F83)
    var otpValue: String by remember { mutableStateOf("") }
    val defaultConfig = OhTeePeeCellConfiguration.withDefaults(
        backgroundColor = backgroundColor.copy(alpha = 0.6f), textStyle = TextStyle(
            color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold
        ), borderColor = Color.Transparent, borderWidth = 1.dp
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Image(
            painter = painterResource(id = R.drawable.sample_image_0),
            contentDescription = "",
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Verification Code",
            fontSize = 24.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Please type the verification code sent to +1111111111",
            color = Color.White,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        OhTeePeeInput(
            value = otpValue,
            onValueChange = { newValue, isValid ->
                otpValue = newValue
            },
            isValueInvalid = otpValue == "1111",
            configurations = OhTeePeeConfigurations.withDefaults(
                cellsCount = 4,
                emptyCellConfig = defaultConfig,
                activeCellConfig = defaultConfig,
                cellModifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(48.dp),
            ),
            autoFocusByDefault = false
        )

        Spacer(modifier = Modifier.height(64.dp))
    }
}

@Composable
private fun Sample1(
    modifier: Modifier = Modifier
) {
    val backgroundColor = Color(0xFFF5CB6C)
    val surfaceColor = Color(0xFFFFE09A)
    var otpValue: String by remember { mutableStateOf("") }
    val defaultConfig = OhTeePeeCellConfiguration.withDefaults(
        backgroundColor = backgroundColor.copy(alpha = 0.6f), textStyle = TextStyle(
            color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Bold
        ), borderColor = Color.Transparent, borderWidth = 1.dp
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Surface(
            shape = CircleShape, color = surfaceColor, modifier = Modifier.size(120.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.sample_image_1),
                contentDescription = "",
                modifier = Modifier.padding(16.dp)
            )
        }


        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Verification Code",
            fontSize = 24.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Please type the verification code sent to +1111111111",
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White, shape = RoundedCornerShape(topStart = 64.dp)),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(64.dp))

            OhTeePeeInput(
                value = otpValue,
                onValueChange = { newValue, isValid ->
                    otpValue = newValue
                },
                isValueInvalid = otpValue == "111111",
                configurations = OhTeePeeConfigurations.withDefaults(
                    cellsCount = 6,
                    emptyCellConfig = defaultConfig,
                    activeCellConfig = defaultConfig,
                    cellModifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(48.dp),
                    obscureText = "●"
                ),
                autoFocusByDefault = false
            )

            Spacer(modifier = Modifier.height(64.dp))

            Text(
                text = "Didn't get the code?", color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Resend OTP",
                color = Color.Black,
                fontSize = 18.sp,
                textDecoration = TextDecoration.Underline,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 48.dp)
                    .height(48.dp)
            ) {
                Text(text = "Submit")
            }
        }
    }
}

@Composable
private fun Sample2(
    modifier: Modifier = Modifier
) {
    val largeRadialGradient = object : ShaderBrush() {
        override fun createShader(size: Size): Shader {
            val biggerDimension = maxOf(size.height, size.width)
            return RadialGradientShader(
                colors = listOf(Color(0xFF2be4dc), Color(0xFF243484)),
                center = size.center,
                radius = biggerDimension / 2f,
                colorStops = listOf(0f, 0.95f)
            )
        }
    }
    val backgroundColor = Color(0xFFFFFFFF)
    var otpValue: String by remember { mutableStateOf("") }
    val defaultConfig = OhTeePeeCellConfiguration.withDefaults(
        borderColor = Color.Transparent,
        borderWidth = 0.dp,
        backgroundColor = backgroundColor.copy(alpha = 0.6f),
        textStyle = TextStyle(
            color = Color.Black
        ),
        shape = RoundedCornerShape(8.dp)
    )

    Row(
        modifier = modifier
            .fillMaxSize()
            .background(largeRadialGradient)
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
                cellsCount = 6,
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
    val backgroundColor = Color(0xFF4F4F83)
    var otpValue: String by remember { mutableStateOf("") }
    val defaultConfig = OhTeePeeCellConfiguration.withDefaults(
        backgroundColor = backgroundColor.copy(alpha = 0.6f), textStyle = TextStyle(
            color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold
        ), borderColor = Color.Transparent, borderWidth = 1.dp
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    listOf(
                        Color(0xFF2be4dc),
                        Color(0xFF243484)
                    )
                )
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Image(
            painter = painterResource(id = R.drawable.sample_image_0),
            contentDescription = "",
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Verification Code",
            fontSize = 24.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Please type the verification code sent to +1111111111",
            color = Color.White,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        OhTeePeeInput(
            value = otpValue,
            onValueChange = { newValue, isValid ->
                otpValue = newValue
            },
            isValueInvalid = otpValue == "1111",
            configurations = OhTeePeeConfigurations.withDefaults(
                cellsCount = 4,
                emptyCellConfig = defaultConfig,
                activeCellConfig = defaultConfig,
                cellModifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(48.dp),
            ),
            autoFocusByDefault = false
        )

        Spacer(modifier = Modifier.height(64.dp))
    }
}

@Composable
private fun Sample4(
    modifier: Modifier = Modifier
) {
    var otpValue: String by remember { mutableStateOf("") }
    val transparentConfig = OhTeePeeCellConfiguration.withDefaults(
        borderColor = Color.Transparent, borderWidth = 0.dp, shape = RoundedCornerShape(0.dp)
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
                cellModifier = Modifier.size(48.dp),
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
            modifier = Modifier.padding(8.dp),
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
                    borderWidth = 2.dp, borderColor = Color.Black
                ),
                errorCellConfig = defaultConfig.copy(
                    borderWidth = 2.dp, borderColor = Color.Red
                ),
                placeHolder = " ",
            ),
            autoFocusByDefault = false
        )
    }
}