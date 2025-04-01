package com.dalmuina.showcase.games.presentation.views

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
import com.dalmuina.showcase.games.presentation.components.CardGame
import com.dalmuina.showcase.games.presentation.components.ErrorItem
import com.dalmuina.showcase.games.presentation.components.Loader
import com.dalmuina.showcase.games.presentation.components.MainTopBar
import com.dalmuina.showcase.games.presentation.components.ShimmerListItem
import com.dalmuina.showcase.games.presentation.model.GameUi
import com.dalmuina.showcase.games.presentation.navigation.Detail
import com.dalmuina.showcase.games.presentation.navigation.SearchGameView
import com.dalmuina.showcase.games.presentation.viewmodel.GamesViewModel
import com.dalmuina.showcase.ui.theme.primaryContainerDark

@Composable
fun HomeViewWrapper(
    viewModel: GamesViewModel,
    modifier: Modifier = Modifier,
    onClickDetail:(Detail)->Unit,
    onClickSearch:(SearchGameView)->Unit
){
    ObserveEvents(viewModel.events)
    val gamesPagingItems = viewModel.gamesPagingFlow.collectAsLazyPagingItems()
    HomeViewScreen(
        gamesPagingItems = gamesPagingItems,
        modifier = modifier,
        onAction = { action ->
            when(action) {
                is GameListAction.NavigateToGame -> onClickSearch(SearchGameView)
                is GameListAction.OnLoadGameDetail -> {
                    onClickDetail(Detail(action.id, null))
                }
                is GameListAction.OnLoadGameDetailSearched -> {
                    onClickDetail(Detail(0,action.search))
                }
                else -> viewModel.onAction(action)
            }
        }
    )
}

@Composable
fun HomeViewScreen(
    gamesPagingItems: LazyPagingItems<GameUi>,
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
    val isLoading = gamesPagingItems.loadState.refresh is LoadState.Loading
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(primaryContainerDark)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        if (isLoading && gamesPagingItems.itemCount == 0) {
            items(5) {  // Show 10 shimmer placeholders
                ShimmerListItem(
                    isLoading = true,
                    contentAfterLoading = { },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        } else {
            items(gamesPagingItems.itemCount) { index ->
                val game = gamesPagingItems[index]
                ShimmerListItem(
                    isLoading = game == null,
                    contentAfterLoading = {
                        if (game != null) {
                            CardGame(game, onClick = { onItemClick(game) })
                            Text(
                                text = game.name,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White,
                                modifier = Modifier.padding(start = 10.dp)
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }

        // Handle loading and error states
        gamesPagingItems.apply {
            when {
                loadState.append is LoadState.Loading -> {
                    item { Loader() }
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

