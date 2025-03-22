package com.dalmuina.showcase.games.presentation

import com.dalmuina.showcase.games.presentation.model.GameUi

sealed interface GameListAction {
    data class OnGameClick(val gameUi:GameUi): GameListAction
    data class OnLoadGameDetail(val id: Int): GameListAction
    object OnBackButtonClick: GameListAction
}
