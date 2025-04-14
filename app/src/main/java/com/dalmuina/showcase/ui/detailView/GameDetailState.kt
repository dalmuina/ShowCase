package com.dalmuina.showcase.ui.detailView

import androidx.compose.runtime.Immutable
import com.dalmuina.model_ui.GameDetailUi

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
