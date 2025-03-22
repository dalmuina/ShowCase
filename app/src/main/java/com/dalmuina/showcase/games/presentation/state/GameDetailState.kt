package com.dalmuina.showcase.games.presentation.state

import androidx.compose.runtime.Immutable
import com.dalmuina.showcase.games.presentation.model.GameDetailUi

@Immutable
data class GameDetailState(
    val isLoading: Boolean = false,
    val gameDetailUi: GameDetailUi = GameDetailUi(
        name = "",
        descriptionRaw = "",
        metacritic = 0,
        website = "",
        backgroundImage = ""
    )
)
