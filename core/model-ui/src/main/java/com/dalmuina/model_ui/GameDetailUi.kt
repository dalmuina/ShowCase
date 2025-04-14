package com.dalmuina.model_ui

import com.dalmuina.model.GameDetail


data class GameDetailUi(
    val name :String,
    val descriptionRaw: String,
    val metacritic: Int?,
    val website: String,
    val backgroundImage: String
)

fun GameDetail.toGameDetailUi() : GameDetailUi {
    return GameDetailUi(
        name = name,
        descriptionRaw= descriptionRaw,
        metacritic= metacritic,
        website = website,
        backgroundImage = backgroundImage
    )
}
