package com.composeuisuite.ohteepee.utils

import androidx.compose.ui.focus.FocusRequester

internal val String.Companion.EMPTY: String
    get() = ""

internal fun String?.orElse(value: String): String {
    return this ?: value
}

internal fun FocusRequester.requestFocusSafely() {
    try {
        this.requestFocus()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}