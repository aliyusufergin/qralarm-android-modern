package com.sweak.qralarm.features.appearance

enum class ThemeMode(val value: Int) {
    SYSTEM(0),
    LIGHT(1),
    DARK(2);

    companion object {
        fun fromValue(value: Int): ThemeMode = entries.find { it.value == value } ?: SYSTEM
    }
}

data class AppearanceScreenState(
    val selectedThemeMode: ThemeMode = ThemeMode.SYSTEM
)
