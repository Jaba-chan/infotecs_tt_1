package ru.evgenykuzakov.infotecs_tt_1.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    showUsersScreenContent: @Composable () -> Unit,
    userDetailInfoScreenContent: @Composable () -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.ShowUsersScreen.route

    ) {
        composable(Screen.ShowUsersScreen.route) {
            showUsersScreenContent()
        }
        composable(
            route = Screen.UserDetailInfoScreen.route,
            arguments = listOf(navArgument("userId") { type = NavType.LongType})
        ) {
            userDetailInfoScreenContent()
        }
    }
}