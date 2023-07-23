package com.tarikyasar.composeotpfield.utils

val String.Companion.EMPTY: String
    get() = ""

fun String?.orElse(value: String): String {
    return this ?: value
}