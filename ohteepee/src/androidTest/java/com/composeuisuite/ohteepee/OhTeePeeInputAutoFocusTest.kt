package com.composeuisuite.ohteepee

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertIsNotFocused
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composeuisuite.ohteepee.configuration.OhTeePeeCellConfiguration
import com.composeuisuite.ohteepee.configuration.OhTeePeeConfigurations
import org.junit.Rule
import org.junit.Test

class OhTeePeeInputAutoFocusTest {
    companion object {
        const val CELLS_COUNT = 6
    }

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun ohTeePeeInput_whenAutoFocusIsTrue_shouldFocusFirstCell() {
        composeTestRule.setContent {
            var otpValue: String by remember { mutableStateOf("") }
            val defaultConfig = OhTeePeeCellConfiguration.withDefaults()

            MaterialTheme {
                BasicOhTeePeeScreen(
                    ohTeePeeInput = {
                        OhTeePeeInput(
                            value = otpValue,
                            onValueChange = { newValue, _ ->
                                otpValue = newValue
                            },
                            configurations = OhTeePeeConfigurations.withDefaults(
                                cellsCount = CELLS_COUNT,
                                emptyCellConfig = defaultConfig,
                                activeCellConfig = defaultConfig.copy(
                                    borderColor = Color.Yellow,
                                ),
                                cellModifier = Modifier.size(48.dp),
                            ),
                        )
                    },
                )
            }
        }

        val cells = composeTestRule.onAllNodesWithTag(OH_TEE_PEE_CELL_TEST_TAG)

        cells.onFirst().assertIsFocused()
    }

    @Test
    fun ohTeePeeInput_whenAutoFocusIsFalse_shouldNotFocusOnAnyCell() {
        composeTestRule.setContent {
            var otpValue: String by remember { mutableStateOf("") }
            val defaultConfig = OhTeePeeCellConfiguration.withDefaults()

            MaterialTheme {
                BasicOhTeePeeScreen(
                    ohTeePeeInput = {
                        OhTeePeeInput(
                            value = otpValue,
                            onValueChange = { newValue, _ ->
                                otpValue = newValue
                            },
                            configurations = OhTeePeeConfigurations.withDefaults(
                                cellsCount = CELLS_COUNT,
                                emptyCellConfig = defaultConfig,
                                activeCellConfig = defaultConfig.copy(
                                    borderColor = Color.Yellow,
                                ),
                                cellModifier = Modifier.size(48.dp),
                            ),
                            autoFocusByDefault = false,
                        )
                    },
                )
            }
        }

        val cells = composeTestRule.onAllNodesWithTag(OH_TEE_PEE_CELL_TEST_TAG)
        repeat(CELLS_COUNT) {
            cells[it].assertIsNotFocused()
        }
    }

    @Composable
    private fun BasicOhTeePeeScreen(
        ohTeePeeInput: @Composable () -> Unit,
        modifier: Modifier = Modifier,
    ) {
        val backgroundColor = Color(0xFF1A1E22)

        Column(
            modifier = modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            ohTeePeeInput()

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                    .padding(vertical = 16.dp, horizontal = 32.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(text = "Continue", fontSize = 24.sp, color = backgroundColor)
            }

            Spacer(modifier = Modifier.height(64.dp))
        }
    }
}
