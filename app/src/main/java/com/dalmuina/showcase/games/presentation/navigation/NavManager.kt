package com.dalmuina.showcase.games.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.dalmuina.showcase.games.presentation.views.DetailView
import com.dalmuina.showcase.games.presentation.views.HomeView
import com.dalmuina.showcase.games.presentation.views.SearchGamesView
import com.dalmuina.showcase.games.presentation.viewmodel.GamesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavManager(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val viewModel = koinViewModel<GamesViewModel>()
    NavHost(navController = navController, startDestination = Home) {
        composable<Home> {
            HomeView(
                navController = navController,
                viewModel = viewModel,
                modifier= modifier
            )
        }
        composable<Detail>{ backStackEntry ->
            val detail = backStackEntry.toRoute<Detail>()
            DetailView(
                navController = navController,
                viewModel = viewModel,
                id= detail.id,
                name = detail.name,
                modifier = modifier
            )
        }
        composable<SearchGameView>{
            SearchGamesView(
                navController = navController,
                viewModel = viewModel,
                modifier = modifier
            )
        }
    }
}
