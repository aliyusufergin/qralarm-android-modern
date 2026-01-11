package com.sweak.qralarm.features.appearance.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sweak.qralarm.features.appearance.AppearanceScreen

const val APPEARANCE_SCREEN_ROUTE = "appearanceScreen"

fun NavController.navigateToAppearance() = navigate(APPEARANCE_SCREEN_ROUTE)

fun NavGraphBuilder.appearanceScreen(
    onBackClicked: () -> Unit
) {
    composable(route = APPEARANCE_SCREEN_ROUTE) {
        AppearanceScreen(
            onBackClicked = onBackClicked
        )
    }
}
