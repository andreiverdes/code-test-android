package com.fueled.technicalchallenge.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

sealed class Screen(val route: String) {
    data object WelcomeScreen: Screen("welcome_screen")
    data object CharacterListScreen: Screen("character_list_screen")
    data object CharacterDetailsScreen: Screen(
        "character_details_screen/"+
                "?${Args.CHARACTER_ID}={${Args.CHARACTER_ID}}"
    ) {

        object Args {
            const val CHARACTER_ID = "CHARACTER_ID"
        }

        fun makeRoute(
            characterId: Long,
        ) = route.arg(Args.CHARACTER_ID, characterId.toString())
    }

    fun String.arg(arg: String, value: String?) =
        value?.let { this.replace("{${arg}}", it) } ?: this
}
