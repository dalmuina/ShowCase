package com.dalmuina.showcase.games.data.mappers

import com.dalmuina.showcase.games.data.models.Game
import com.dalmuina.showcase.games.data.network.dto.GameDto

fun GameDto.toGame(): Game{
    return Game(
        id = id,
        name = name,
        backgroundImage = backgroundImage
    )
}
