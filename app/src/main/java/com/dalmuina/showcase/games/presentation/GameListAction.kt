package com.dalmuina.showcase.games.presentation

sealed interface GameListAction {
    data class OnFilterChange(val filter:String): GameListAction
    data class OnLoadGameDetail(val id: Int): GameListAction
    object OnBackButtonClick: GameListAction
}
