package com.guizot.android_clean_architecture_boilerplate.core.presentation.utils


fun String.toReadableFormat(): String {
    return this.replace(Regex("([a-z])([A-Z])"), "$1 $2") // Add space before uppercase letters
        .replace(Regex("([A-Z])([A-Z][a-z])"), "$1 $2") // Handle consecutive uppercase letters
        .split(" ") // Split into words
        .joinToString(" ") { it.replaceFirstChar { char -> char.uppercaseChar() } } // Capitalize each word
}
