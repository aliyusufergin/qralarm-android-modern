package com.sweak.qralarm.features.appearance

sealed class AppearanceScreenUserEvent {
    data object OnBackClicked : AppearanceScreenUserEvent()
    data class OnThemeModeSelected(val themeMode: ThemeMode) : AppearanceScreenUserEvent()
}
