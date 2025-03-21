package com.dalmuina.showcase.games.presentation.model

import com.dalmuina.showcase.games.domain.model.Game

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
