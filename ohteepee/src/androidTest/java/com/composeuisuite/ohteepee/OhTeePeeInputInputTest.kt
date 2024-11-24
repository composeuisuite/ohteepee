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
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.unit.dp
import com.composeuisuite.ohteepee.configuration.OH_TEE_PEE_DEFAULT_PLACE_HOLDER
import com.composeuisuite.ohteepee.configuration.OhTeePeeCellConfiguration
import com.composeuisuite.ohteepee.configuration.OhTeePeeConfigurations
import com.composeuisuite.ohteepee.helpers.sleepThreadToSeeUiTestResults
import com.composeuisuite.ohteepee.utils.EMPTY
import org.junit.Assert
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
        var currentOtpValue = ""
        setContentForClearInputTest(
            onValueChange = {
                currentOtpValue = it
            },
        )

        val cells = composeTestRule.onAllNodesWithTag(OH_TEE_PEE_CELL_TEST_TAG)

        repeat(CELLS_COUNT) {
            cells[it].performTextInput("1")
        }
        sleepThreadToSeeUiTestResults()

        composeTestRule.runOnIdle {
            Assert.assertEquals("Current otp value should be empty", String.EMPTY, currentOtpValue)
        }

        repeat(CELLS_COUNT) {
            cells[it].assertTextContains(" ")
        }
    }

    @Test
    fun ohTeePeeInput_whenClearInputOnErrorIsTrueAndOTPIsValid_shouldNotClearInput() {
        var currentOtpValue = ""
        setContentForClearInputTest(
            onValueChange = {
                currentOtpValue = it
            },
        )

        val cells = composeTestRule.onAllNodesWithTag(OH_TEE_PEE_CELL_TEST_TAG)

        repeat(CELLS_COUNT) {
            cells[it].performTextInput("${it + 1}")
        }

        composeTestRule.runOnIdle {
            Assert.assertEquals(
                "Current otp value should be equal to the entered code",
                VALID_INPUT,
                currentOtpValue,
            )
        }

        repeat(CELLS_COUNT) {
            cells[it].assertTextEquals("${it + 1}")
        }

        sleepThreadToSeeUiTestResults()
    }

    @Test
    fun ohTeePeeInput_whenEnteringCode_shouldOnValueChangeTriggeredWithCorrectValues() {
        var currentOtpValue = ""
        var expectedValue = ""

        composeTestRule.setContent {
            var otpValue: String by remember { mutableStateOf("") }
            val defaultConfig = OhTeePeeCellConfiguration.withDefaults()

            MaterialTheme {
                BasicOhTeePeeTestScreen(
                    ohTeePeeInput = {
                        OhTeePeeInput(
                            value = otpValue,
                            onValueChange = { newValue, _ ->
                                otpValue = newValue
                                currentOtpValue = newValue.filter { it != OH_TEE_PEE_DEFAULT_PLACE_HOLDER }
                            },
                            configurations = OhTeePeeConfigurations.withDefaults(
                                cellsCount = CELLS_COUNT,
                                emptyCellConfig = defaultConfig,
                            ),
                        )
                    },
                )
            }
        }

        val cells = composeTestRule.onAllNodesWithTag(OH_TEE_PEE_CELL_TEST_TAG)

        // Test1: Fill the component with 012345
        repeat(CELLS_COUNT) {
            cells[it].performTextInput("$it")
            expectedValue += it
            Assert.assertEquals(expectedValue, currentOtpValue)
        }

        // Test2: Replace the first cell with 6 -> 612345
        cells.onFirst().performTextInput("6")
        expectedValue = "6" + expectedValue.substring(1)
        Assert.assertEquals("Current otp isn't equal to expected value", expectedValue, currentOtpValue)

        // Test3: Clear the otp code
        repeat(CELLS_COUNT) {
            cells[it].performTextInput(" ")
        }
        Assert.assertEquals("Current otp value should be empty", String.EMPTY, currentOtpValue)
    }

    private fun setContentForClearInputTest(
        onValueChange: (String) -> Unit,
    ) {
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
                                onValueChange(newValue)
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
