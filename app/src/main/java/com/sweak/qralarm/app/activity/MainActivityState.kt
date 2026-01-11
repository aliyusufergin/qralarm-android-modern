package com.sweak.qralarm.app.activity

data class MainActivityState(
    val shouldShowSplashScreen: Boolean = true,
    val isIntroductionFinished: Boolean? = null,
    val themeMode: Int = 0 // 0 = System, 1 = Light, 2 = Dark
)
