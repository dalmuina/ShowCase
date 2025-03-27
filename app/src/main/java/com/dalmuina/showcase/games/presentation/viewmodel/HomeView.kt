package com.dalmuina.showcase.games.presentation.viewmodel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.dalmuina.core.presentation.util.ObserveEvents
import com.dalmuina.showcase.games.presentation.GameListAction
import com.dalmuina.showcase.games.presentation.component.CardGame
import com.dalmuina.showcase.games.presentation.component.ErrorItem
import com.dalmuina.showcase.games.presentation.component.FullScreenLoading
import com.dalmuina.showcase.games.presentation.component.LoadingItem
import com.dalmuina.showcase.games.presentation.component.MainTopBar
import com.dalmuina.showcase.games.presentation.model.GameUi
import com.dalmuina.showcase.ui.theme.primaryContainerDark

@Composable
fun HomeView(
    navController: NavController,
    viewModel: GamesViewModel,
    modifier: Modifier = Modifier,
){
    ObserveEvents(viewModel.events)

    val gamesPagingItems = viewModel.gamesPagingFlow.collectAsLazyPagingItems()
    HomeViewScreen(
        gamesPagingItems = gamesPagingItems,
        modifier = modifier,
        onAction = { action ->
            when(action) {
                is GameListAction.NavigateToGame -> navController.navigate("SearchGameView")
                is GameListAction.OnLoadGameDetail -> {
                    navController.navigate("DetailView/${action.id}/")
                }
                is GameListAction.OnLoadGameDetailSearched -> {
                    navController.navigate("DetailView/0/?${action.search}")
                }
                else -> viewModel.onAction(action)
            }
        }
    )
}

@Composable
fun HomeViewScreen(gamesPagingItems: LazyPagingItems<GameUi>,
                   modifier: Modifier,
                   onAction:(GameListAction)->Unit
){
    var search by remember { mutableStateOf("") }
    Scaffold(
        modifier = modifier,
        topBar = {
            MainTopBar(
                title = "API GAMES",
                onClickBackButton = {}){
                onAction(GameListAction.NavigateToGame("SearchGameView"))
            }
        }
    ) {padding->
        Column(
            modifier = Modifier.padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchField(search, onSearchChange = { search = it }, onSearch = {
                onAction(GameListAction.OnLoadGameDetailSearched(search))
            })

            GameListContent(
                gamesPagingItems = gamesPagingItems,
                onItemClick = { game ->
                    onAction(GameListAction.OnLoadGameDetail(game.id))
                }
            )
        }
    }
}
@Composable
fun GameListContent(
    gamesPagingItems: LazyPagingItems<GameUi>,
    onItemClick: (GameUi) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(primaryContainerDark)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        items(gamesPagingItems.itemCount) { index ->
            gamesPagingItems[index]?.let { game ->
                CardGame(game, onClick = { onItemClick(game) })
                Text(
                    text = game.name,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }

        // Handle loading and error states
        gamesPagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { FullScreenLoading() }
                }
                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }
                loadState.refresh is LoadState.Error -> {
                    item { ErrorItem(onRetry = { retry() }) }
                }
                loadState.append is LoadState.Error -> {
                    item { ErrorItem(onRetry = { retry() }) }
                }
            }
        }
    }
}

@Composable
fun SearchField(
    value: String,
    onSearchChange: (String) -> Unit,
    onSearch: () -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onSearchChange,
        label = { Text("Search") },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { onSearch() }),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 0.dp)
    )
    Spacer(modifier = Modifier.height(16.dp))
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewContentHomeView() {
//    ShowCaseTheme {
//        val mockGames = listOf(
//            GameUi(id = 1, name = "GTA","https://media-rockstargames-com.akamaized.net/mfe6/prod" +
//                    "/__common/img/71d4d17edcd49703a5ea446cc0e588e6.jpg"),
//            GameUi(id = 1, name = "Read Dead Redemption","Image"),
//            GameUi(id = 1, name = "Tetris","Image"),
//        )
//
//        ContentHomeView(
//            pad = PaddingValues(16.dp),
//            games = mockGames,
//            gamesPage =
//            onAction = { action->}
//        )
//    }
//}
