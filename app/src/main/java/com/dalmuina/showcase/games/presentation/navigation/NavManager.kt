package com.dalmuina.showcase.games.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dalmuina.showcase.games.presentation.DetailView
import com.dalmuina.showcase.games.presentation.HomeView
import com.dalmuina.showcase.games.presentation.state.GameListState

@Composable
fun NavManager(
    state: GameListState,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home") {
        composable("Home") {
            HomeView(navController = navController,state = state,modifier= modifier)
        }
        composable("DetailView/{id}", arguments = listOf(
            navArgument("id") { type = NavType.IntType }
        )  ){
            val id = it.arguments?.getInt("id") ?: 0
            DetailView(navController = navController, id= id, modifier = modifier)
        }
    }

}
