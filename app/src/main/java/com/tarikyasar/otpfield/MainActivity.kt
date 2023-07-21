package com.tarikyasar.otpfield

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.tarikyasar.composeotpfield.OtpComposable
import com.tarikyasar.otpfield.ui.theme.OtpFieldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OtpFieldTheme {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    OtpComposable(
                        isOtpValid = {
                            println(it)
                        },
                        codeLength = 6
                    )
                }
            }
        }
    }
}