package com.composeuisuite.ohteepee

import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertIsNotFocused
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.unit.dp
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
                BasicOhTeePeeTestScreen(
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
                BasicOhTeePeeTestScreen(
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

    @Test
    fun ohTeePeeInput_basicFlow_shouldFocusOnTheNextCellAfterTextInput() {
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
                            },
                            configurations = OhTeePeeConfigurations.withDefaults(
                                cellsCount = CELLS_COUNT,
                                emptyCellConfig = defaultConfig,
                                activeCellConfig = defaultConfig.copy(
                                    borderColor = Color.Yellow,
                                ),
                                cellModifier = Modifier.size(48.dp),
                            ),
                            autoFocusByDefault = true,
                        )
                    },
                )
            }
        }

        // First cell is focused by default
        val cells = composeTestRule.onAllNodesWithTag(OH_TEE_PEE_CELL_TEST_TAG)
        repeat(CELLS_COUNT) {
            cells[it].assertIsFocused()
            cells[it].performTextInput("$it")
        }
        cells.onLast().assertIsFocused()

        // Test user clicking to in random cell to modify a cell.
        cells[2].performClick().performTextInput("9")
        cells[3].assertIsFocused()
        cells[3].performTextInput("7")
        cells[4].assertIsFocused()
    }
}
