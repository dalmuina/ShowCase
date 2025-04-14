package com.dalmuina.model_ui

import androidx.compose.runtime.Immutable
import com.dalmuina.model.Game

@Immutable
data class GameUi(
    val id:Int,
    val name:String,
    val backgroundImage: String
)

fun Game.toGameUi() = GameUi(id, name, backgroundImage)
