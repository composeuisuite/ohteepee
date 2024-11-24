package com.composeuisuite.ohteepee.configuration

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.composeuisuite.ohteepee.OhTeePeeDefaults

data class OhTeePeeCellConfiguration(
    val shape: Shape,
    val backgroundColor: Color,
    val borderColor: Color,
    val borderWidth: Dp,
    val textStyle: TextStyle,
    val placeHolderTextStyle: TextStyle,
) {
    companion object {
        @Deprecated(
            message = "All default values are move to OhTeePeeDefaults",
            replaceWith = ReplaceWith(
                expression = "OhTeePeeDefaults.BORDER_WIDTH",
                imports = ["com.composeuisuite.ohteepee.OhTeePeeDefaults"],
            ),
        )
        val BORDER_WIDTH = OhTeePeeDefaults.BORDER_WIDTH

        @Deprecated(
            message = "This function is moved to OhTeePeeDefaults.cellConfiguration",
        )
        @Composable
        fun withDefaults(
            shape: Shape = MaterialTheme.shapes.medium,
            backgroundColor: Color = MaterialTheme.colors.surface,
            borderColor: Color = MaterialTheme.colors.primary,
            borderWidth: Dp = OhTeePeeDefaults.BORDER_WIDTH,
            textStyle: TextStyle = TextStyle(),
            placeHolderTextStyle: TextStyle = textStyle,
        ) = OhTeePeeDefaults.cellConfiguration(
            shape = shape,
            backgroundColor = backgroundColor,
            borderColor = borderColor,
            borderWidth = borderWidth,
            textStyle = textStyle,
            placeHolderTextStyle = placeHolderTextStyle,
        )
    }
}
