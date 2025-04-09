package com.dalmuina.showcase.games.presentation.model

import androidx.compose.runtime.Immutable
import com.dalmuina.model.Game

@Immutable
data class GameUi(
    val id:Int,
    val name:String,
    val backgroundImage: String
)

fun Game.toGameUi() = GameUi(id, name, backgroundImage)

internal val previewGameUi = GameUi(
    id = 1,
    name = "GTA V",
    backgroundImage = "https://example_image.jpg"
)
