package com.dalmuina.showcase.games.domain.model

import com.dalmuina.showcase.games.data.network.dto.GameDto

data class Game(
    val id:Int,
    val name:String,
    val backgroundImage: String
)

fun GameDto.toGame(): Game {
    return Game(
        id = id,
        name = name,
        backgroundImage = backgroundImage
    )
}
