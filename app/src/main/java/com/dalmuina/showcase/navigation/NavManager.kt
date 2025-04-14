package com.dalmuina.showcase.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.dalmuina.showcase.ui.UiViewModel
import com.dalmuina.showcase.ui.detailView.DetailViewWrapper
import com.dalmuina.showcase.ui.homeView.HomeViewWrapper
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavManager(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val viewModel = koinViewModel<UiViewModel>()
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
