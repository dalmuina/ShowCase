package com.dalmuina.showcase.games.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dalmuina.showcase.games.presentation.components.CardGame
import com.dalmuina.showcase.games.presentation.components.MainTopBar
import com.dalmuina.showcase.games.presentation.models.GameUi
import com.dalmuina.showcase.games.presentation.viewmodels.GamesViewModel
import com.dalmuina.showcase.ui.theme.ShowCaseTheme
import com.dalmuina.showcase.ui.theme.primaryContainerDark

@Composable
fun HomeView(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: GamesViewModel = hiltViewModel()
){
    val games by viewModel.games.collectAsState()
    Scaffold(
        modifier = modifier,
        topBar = {
            MainTopBar(title = "API GAMES", onClickBackButton = {})
        }
    ) {
        ContentHomeView(it, games){
            navController.navigate("DetailView/${it}")
        }
    }

}

@Composable
fun ContentHomeView(pad: PaddingValues, games: List<GameUi>, onClick:(id:Int)->Unit){
    LazyColumn(
        modifier = Modifier
            .padding(pad)
            .background(primaryContainerDark)
    ){
        items(games){ item ->
            CardGame(item) {
                onClick(item.id)
            }
            item.name?.let {
                Text(text = it,
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
            onClick = {}
        )
    }
}
