package com.composeuisuite.ohteepee.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester

internal val String.Companion.EMPTY: String
    get() = ""

internal fun FocusRequester.requestFocusSafely() {
    try {
        this.requestFocus()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

internal fun Modifier.conditional(condition: Boolean, modifier: Modifier.() -> Modifier): Modifier {
    return if (condition) {
        then(modifier(Modifier))
    } else {
        this
    }
}
