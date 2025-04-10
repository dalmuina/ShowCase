package com.dalmuina.showcase.games.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.dalmuina.showcase.games.presentation.viewmodel.GamesViewModel
import com.dalmuina.showcase.games.presentation.views.DetailViewWrapper
import com.dalmuina.showcase.games.presentation.views.HomeViewWrapper
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavManager(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val viewModel = koinViewModel<GamesViewModel>()
    NavHost(navController = navController, startDestination = Home) {
        composable<Home> {
            HomeViewWrapper(
                viewModel = viewModel,
                modifier= modifier,
                onClickDetail = {detail->
                    navController.navigate(detail)
                }
            )
        }
        composable<Detail>{ backStackEntry ->
            val detail = backStackEntry.toRoute<Detail>()
            DetailViewWrapper(
                viewModel = viewModel,
                id= detail.id,
                modifier = modifier,
                onClickBack = {
                    navController.popBackStack(Home,false)
                }
            )
        }
    }
}
