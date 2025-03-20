package com.dalmuina.showcase.games.presentation.models

import com.dalmuina.showcase.games.domain.models.Game

data class GameUi(
    val id:Int,
    val name:String,
    val backgroundImage: String
)

fun Game.toGameUi() = GameUi(id, name, backgroundImage)
