package com.dalmuina.showcase.games.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp import androidx.navigation.NavController
import com.dalmuina.core.presentation.util.NetworkErrorEvent
import com.dalmuina.core.presentation.util.ObserveEvents
import com.dalmuina.showcase.games.presentation.component.CardGame
import com.dalmuina.showcase.games.presentation.component.MainTopBar
import com.dalmuina.showcase.games.presentation.model.GameUi
import com.dalmuina.showcase.games.presentation.state.GameListState
import com.dalmuina.showcase.ui.theme.ShowCaseTheme
import com.dalmuina.showcase.ui.theme.primaryContainerDark
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeView(
    navController: NavController,
    state : GameListState,
    events : Flow<NetworkErrorEvent>,
    modifier: Modifier = Modifier,
){
    ObserveEvents(events)
    Scaffold(
        modifier = modifier,
        topBar = {
            MainTopBar(title = "API GAMES", onClickBackButton = {}){
                navController.navigate("SearchGameView")
            }
        }
    ) {
        if (state.isLoading) {
            Box(
                modifier = modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        } else {
            ContentHomeView(it, state.games, onSearchChanged = { search ->
                val zero = 0
                navController.navigate("DetailView/${zero}/${search}")
            }) {
                navController.navigate("DetailView/${it}/${""}")
            }
        }
    }
}


@Composable
fun ContentHomeView(pad: PaddingValues, games: List<GameUi>, onSearchChanged:(String)->Unit, onClick:(Int)->Unit){
    var search by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .padding(pad),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = search,
            onValueChange = {search = it},
            label = {Text(text = "Search")},
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onSearchChanged(search)
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp,0.dp)

        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier
                .background(primaryContainerDark)
                .padding(horizontal = 16.dp)
        ){
            items(games){ item ->
                CardGame(item) {
                    onClick(item.id)
                }

                Text(text = item.name,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewContentHomeView() {
    ShowCaseTheme {
        val mockGames = listOf(
            GameUi(id = 1, name = "GTA","https://media-rockstargames-com.akamaized.net/mfe6/prod" +
                    "/__common/img/71d4d17edcd49703a5ea446cc0e588e6.jpg"),
            GameUi(id = 1, name = "Read Dead Redemption","Image"),
            GameUi(id = 1, name = "Tetris","Image"),
        )
        ContentHomeView(
            pad = PaddingValues(16.dp),
            games = mockGames,
            onSearchChanged = {},
            onClick = {}
        )
    }
}
