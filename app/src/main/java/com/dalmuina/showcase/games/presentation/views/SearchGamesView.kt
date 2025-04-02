package com.dalmuina.showcase.games.presentation.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dalmuina.core.presentation.util.ObserveEvents
import com.dalmuina.showcase.games.presentation.GameListAction
import com.dalmuina.showcase.games.presentation.navigation.Detail
import com.dalmuina.showcase.games.presentation.state.GameListState
import com.dalmuina.showcase.games.presentation.viewmodel.GamesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchGamesViewWrapper(
    viewModel: GamesViewModel,
    modifier: Modifier = Modifier,
    onClickDetail:(Detail)->Unit,
    onClickBack:()->Unit
) {

    ObserveEvents(viewModel.events)
    val state by viewModel.state.collectAsStateWithLifecycle()
    SearchGamesViewScreen(
        modifier,
        state,
        onAction = {action ->
            when(action) {
                is GameListAction.OnLoadGameDetail -> {
                    onClickDetail(Detail(action.id,null))
                }
                GameListAction.OnBackButtonClick -> {
                    onClickBack
                }
                else -> viewModel.onAction(action)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchGamesViewScreen(
    modifier: Modifier = Modifier,
    state: GameListState,
    onAction:(GameListAction)->Unit
) {
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    Scaffold(modifier = modifier) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 16.dp),
            ) {
            SearchBar(
                modifier = modifier.fillMaxWidth(),
                inputField = {
                    TextField(
                        value = query,
                        onValueChange = {query = it},
                        placeholder = {Text(text = "Search")},
                        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search")},
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close",
                                modifier = Modifier.clickable {
                                    if (query.isNotEmpty()) { query= "" }
                                    else { onAction(GameListAction.OnBackButtonClick) }
                                }
                            )
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Transparent,
                            disabledTextColor = Color.Transparent
                        ),
                        modifier = modifier
                            .fillMaxWidth()
                            .onFocusChanged { focusState ->
                                if (focusState.isFocused) active = true
                            }
                    )
                },
                expanded = active,
                onExpandedChange = {active = it},
                shape = MaterialTheme.shapes.medium,
                colors = SearchBarDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.surface,
                ),
                tonalElevation = 4.dp,
                shadowElevation = 4.dp,
                windowInsets = WindowInsets.systemBars
            ) {
               SearchBarContent(query,state) {gameId->
                   onAction(gameId)
               }
            }
            CheckState(active,state) {gameId->
                onAction(gameId)
            }
        }
    }
}

@Composable
fun CheckState(active: Boolean,state: GameListState, onAction:(GameListAction)->Unit) {
    if (!active) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.games, key = {it.id}) { game ->
                Text(
                    text = game.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(bottom = 10.dp, start = 10.dp)
                        .clickable {
                            onAction(GameListAction.OnLoadGameDetail(game.id))
                        }
                )
            }
        }
    }
}

@Composable
fun SearchBarContent( query: String,state: GameListState, onAction:(GameListAction)->Unit) {
    if (query.isNotEmpty() && query.length > 2) {
        val filteredGames = state.games.filter { game ->
            game.name.contains(query, ignoreCase = true)
        }
        if (filteredGames.isNotEmpty()) {
            LazyColumn {
                items(filteredGames, key = {it.id}) { game ->
                    Text(
                        text = game.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(bottom = 10.dp, start = 10.dp)
                            .clickable {
                                onAction(GameListAction.OnLoadGameDetail(game.id))
                            }
                    )
                }
            }
        } else {
            Text(
                text = "No results found",
                fontSize = 16.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
