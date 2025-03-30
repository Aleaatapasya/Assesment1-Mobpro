package com.aleaatapasya0002.assesment1.navigation

sealed class Screen(val route: String) {
    data object Home:Screen("mainScreen")
    data object About:Screen("aboutScreen")
    data object Tutorial:Screen("tutorialScreen")
}