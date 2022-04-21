package com.example.borutoapp.navigtion

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.borutoapp.presentation.screens.home.HomeScreen
import com.example.borutoapp.presentation.screens.search.SearchScreen
import com.example.borutoapp.presentation.screens.splash.SplashScreen
import com.example.borutoapp.presentation.screens.welcome.WelcomeScreen
import com.example.borutoapp.util.Constants.HERO_ID_ARGUMENT_KEY

@Composable
fun SetupNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navHostController = navController)
        }
        composable(Screen.Welcome.route) {
            WelcomeScreen(navHostController = navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(navHostController = navController)
        }
        composable(
            Screen.Detail.route, arguments = listOf(
                navArgument(
                    HERO_ID_ARGUMENT_KEY
                ) {
                    type = NavType.IntType
                }
            )
        ) {

        }
        composable(Screen.Search.route) {
            SearchScreen(navHostController = navController)
        }
    }
}
