package com.tarikyasar.composeotpfield

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.unit.dp

@Composable
fun OtpComposable(
    isOtpValid: (String) -> Unit,
    codeLength: Int = 6
) {
    val otpValue = remember {
        mutableStateOf(CharArray(codeLength) { '-' }).value
    }

    val focusRequester = remember {
        List(codeLength) { FocusRequester() }
    }

    LaunchedEffect(Unit) {
        focusRequester.first().requestFocus()
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(codeLength) { index ->
            ComposeOtpField(
                modifier = Modifier
                    .absolutePadding(left = 2.dp, right = 1.dp)
                    .weight(1f)
                    .focusOrder(focusRequester = focusRequester[index])
                    .clip(CircleShape)
                    .border(
                        BorderStroke(
                            1.dp,
                            MaterialTheme.colors.primary
                        ), CircleShape
                    ),
                onValueChange = {
                    if (it.isBlank()) {
                        focusRequester[(index - 1).coerceIn(0, codeLength - 1)].requestFocus()
                        otpValue.set(index = index, value = '-')
                    } else {
                        focusRequester[(index + 1).coerceIn(0, codeLength - 1)].requestFocus()
                        otpValue[index] = it.first()
                    }
                    isOtpValid(otpValue.joinToString(""))
                }
            )
        }
    }
}