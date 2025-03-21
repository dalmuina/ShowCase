package com.dalmuina.showcase.games.presentation.state

import androidx.compose.runtime.Immutable
import com.dalmuina.showcase.games.presentation.model.GameUi

@Immutable
data class GameListState(
    val isLoading: Boolean = false,
    val games: List<GameUi> = emptyList(),
    val selectedGame: GameUi? = null
)
