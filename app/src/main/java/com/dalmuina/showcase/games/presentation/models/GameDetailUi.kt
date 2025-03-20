package com.dalmuina.showcase.games.presentation.models

import com.dalmuina.showcase.games.domain.models.GameDetail

data class GameDetailUi(
    val name :String ="",
    val descriptionRaw: String ="",
    val metacritic: Int = 0,
    val website: String =" ",
    val backgroundImage: String = ""
)

fun GameDetail.toGameDetailUi() = GameDetailUi(name, descriptionRaw, metacritic, website, backgroundImage)
