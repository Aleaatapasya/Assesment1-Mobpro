package com.aleaatapasya0002.assesment1.navigation

sealed class Screen(val route: String) {
    data object Home:Screen("mainScreen")
}