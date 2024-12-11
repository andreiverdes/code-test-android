package com.fueled.technicalchallenge

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fueled.technicalchallenge.presentation.Screen
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
        addChatacterListScreen(navController)
        addCharacterDetailsScreen(navController)
    }
}

private fun NavGraphBuilder.addCharacterDetailsScreen(navController: NavHostController) {
    composable(
        route = Screen.CharacterDetailsScreen.route
    ) {
        WelcomeScreen {
            navController.navigate(Screen.CharacterListScreen.route)
        }
    }
}

private fun NavGraphBuilder.addChatacterListScreen(navController: NavHostController) {
    composable(
        route = Screen.CharacterListScreen.route
    ) {
        CharacterListScreen()
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