package com.fueled.technicalchallenge

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fueled.technicalchallenge.presentation.Screen
import com.fueled.technicalchallenge.presentation.character.details.CharacterDetailsScreen
import com.fueled.technicalchallenge.presentation.character.list.CharacterListScreen
import com.fueled.technicalchallenge.presentation.welcome.WelcomeScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.WelcomeScreen.route,
    ) {
        addWelcomeScreen(navController)
        addCharacterListScreen(navController)
        addCharacterDetailsScreen(navController)
    }
}

private fun NavGraphBuilder.addCharacterDetailsScreen(navController: NavHostController) {
    composable(
        route = Screen.CharacterDetailsScreen.route,
        arguments = listOf(
            navArgument(
                name = Screen.CharacterDetailsScreen.Args.CHARACTER_ID
            ) {
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->
        val characterId = backStackEntry.arguments
            ?.getInt(Screen.CharacterDetailsScreen.Args.CHARACTER_ID)
            ?: -1
        CharacterDetailsScreen(characterId = characterId) {
            navController.navigateUp()
        }
    }
}

private fun NavGraphBuilder.addCharacterListScreen(navController: NavHostController) {
    composable(
        route = Screen.CharacterListScreen.route
    ) {
        CharacterListScreen {
            navController.navigate(Screen.CharacterDetailsScreen.makeRoute(it.id))
        }
    }
}

private fun NavGraphBuilder.addWelcomeScreen(navController: NavHostController) {
    composable(
        route = Screen.WelcomeScreen.route
    ) {
        WelcomeScreen {
            navController.navigate(Screen.CharacterListScreen.route)
        }
    }
}