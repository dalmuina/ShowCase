package com.dalmuina.showcase.games.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dalmuina.showcase.games.presentation.viewmodel.DetailView
import com.dalmuina.showcase.games.presentation.viewmodel.HomeView
import com.dalmuina.showcase.games.presentation.viewmodel.SearchGamesView
import com.dalmuina.showcase.games.presentation.viewmodel.GamesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavManager(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val viewModel = koinViewModel<GamesViewModel>()
    NavHost(navController = navController, startDestination = "Home") {
        composable("Home") {
            HomeView(
                navController = navController,
                viewModel = viewModel,
                modifier= modifier
            )
        }
        composable("DetailView/{id}/?{name}", arguments = listOf(
            navArgument("id") { type = NavType.IntType },
            navArgument("name") {
                type = NavType.StringType
                nullable= true
                defaultValue = ""
            }
        )  ){
            val id = it.arguments?.getInt("id") ?: 0
            val name= it.arguments?.getString("name")?:""
            DetailView(
                navController = navController,
                viewModel = viewModel,
                id= id,
                name = name,
                modifier = modifier
            )
        }
        composable("SearchGameView"){
            SearchGamesView(
                navController = navController,
                viewModel = viewModel,
                modifier = modifier
            )
        }
    }
}
