package com.dalmuina.showcase.games.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dalmuina.showcase.games.presentation.DetailView
import com.dalmuina.showcase.games.presentation.HomeView
import com.dalmuina.showcase.games.presentation.SearchGamesView
import com.dalmuina.showcase.games.presentation.viewmodel.GamesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavManager(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val viewModel = koinViewModel<GamesViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()
    NavHost(navController = navController, startDestination = "Home") {
        composable("Home") {
            HomeView(navController = navController,
                state = state,
                events = viewModel.events,
                modifier= modifier)
        }
        composable("DetailView/{id}/{name}", arguments = listOf(
            navArgument("id") { type = NavType.IntType },
            navArgument("name") {type = NavType.StringType}
        )  ){
            val detail by viewModel.detail.collectAsStateWithLifecycle()
            val id = it.arguments?.getInt("id") ?: 0
            val name= it.arguments?.getString("name")?:""
            DetailView(navController = navController,
                detail = detail,
                onAction = viewModel::onAction,
                events = viewModel.events,
                id= id,
                name = name,
                modifier = modifier)
        }
        composable("SearchGameView"){
            SearchGamesView(
                navController = navController,
                state = state,
                events = viewModel.events,
                modifier = modifier
            )
        }
    }
}
