package com.dalmuina.showcase.games.presentation.models

import com.dalmuina.showcase.games.data.models.Game

data class GamesUi(
    val count: Int,
    val result: List<GameUi>
)

data class GameUi(
    val id:Int,
    val name:String?,
    val backgroundImage: String?
)

fun Game.toDomain() = GameUi(id, name, backgroundImage)
