package com.composeuisuite.ohteepee

import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.unit.dp
import com.composeuisuite.ohteepee.configuration.OhTeePeeCellConfiguration
import com.composeuisuite.ohteepee.configuration.OhTeePeeConfigurations
import com.composeuisuite.ohteepee.helpers.sleepThreadToSeeUiTestResults
import org.junit.Rule
import org.junit.Test

class OhTeePeeInputInputTest {
    companion object {
        private const val CELLS_COUNT = 6
        private const val VALID_INPUT = "123456"
    }

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun ohTeePeeInput_whenClearInputOnErrorIsTrueAndOTPIsNotValid_shouldClearInputAfterError() {
        setContentForClearInputTest()

        val cells = composeTestRule.onAllNodesWithTag(OH_TEE_PEE_CELL_TEST_TAG)

        repeat(CELLS_COUNT) {
            cells[it].performTextInput("1")
        }
        sleepThreadToSeeUiTestResults()

        repeat(CELLS_COUNT) {
            cells[it].assertTextContains(" ")
        }
    }

    @Test
    fun ohTeePeeInput_whenClearInputOnErrorIsTrueAndOTPIsValid_shouldNotClearInputAfterError() {
        setContentForClearInputTest()

        val cells = composeTestRule.onAllNodesWithTag(OH_TEE_PEE_CELL_TEST_TAG)

        repeat(CELLS_COUNT) {
            cells[it].performTextInput("${it + 1}")
        }

        repeat(CELLS_COUNT) {
            cells[it].assertTextEquals("${it + 1}")
        }
        sleepThreadToSeeUiTestResults()
    }

    private fun setContentForClearInputTest() {
        composeTestRule.setContent {
            var otpValue: String by remember { mutableStateOf("") }
            val defaultConfig = OhTeePeeCellConfiguration.withDefaults()
            var isInValidValue by remember {
                mutableStateOf(false)
            }

            MaterialTheme {
                BasicOhTeePeeTestScreen(
                    ohTeePeeInput = {
                        OhTeePeeInput(
                            value = otpValue,
                            onValueChange = { newValue, isValid ->
                                otpValue = newValue
                                isInValidValue = if (isValid) {
                                    otpValue != VALID_INPUT
                                } else {
                                    false
                                }
                            },
                            isValueInvalid = isInValidValue,
                            configurations = OhTeePeeConfigurations.withDefaults(
                                cellsCount = CELLS_COUNT,
                                emptyCellConfig = defaultConfig,
                                activeCellConfig = defaultConfig.copy(
                                    borderColor = Color.Yellow,
                                ),
                                cellModifier = Modifier.size(48.dp),
                                clearInputOnError = true,
                            ),
                        )
                    },
                )
            }
        }
    }
}
