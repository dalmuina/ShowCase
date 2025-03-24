package com.dalmuina.showcase.games.presentation

import com.dalmuina.showcase.games.presentation.model.GameUi

sealed interface GameListAction {
    data class OnGameClick(val gameUi:GameUi): GameListAction
    data class OnLoadGameDetail(val id: Int): GameListAction
    data class OnLoadGameDetailSearched(val search: String): GameListAction
    object OnBackButtonClick: GameListAction
}
