package com.dalmuina.showcase.games.domain.model

import com.dalmuina.showcase.games.data.network.dto.GameDetailDto

data class GameDetail(
    val name : String,
    val descriptionRaw : String,
    val metacritic: Int,
    val website : String,
    val backgroundImage: String
)

fun GameDetailDto.toGameDetail(): GameDetail{
    return GameDetail(
        name = name,
        descriptionRaw = descriptionRaw,
        metacritic = metacritic,
        website = website,
        backgroundImage = backgroundImage
    )
}
