package com.fueled.technicalchallenge.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

sealed class Screen(val route: String) {
    object WelcomeScreen: Screen("welcome_screen")
    object CharacterListScreen: Screen("character_list_screen")
    object CharacterDetailsScreen: Screen("character_details_screen")
}
