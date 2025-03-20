package com.dalmuina.showcase.games.data.mappers

import com.dalmuina.showcase.games.data.network.dto.GameDto
import com.dalmuina.showcase.games.domain.models.Game

fun GameDto.toGame(): Game{
    return Game(
        id = id,
        name = name,
        backgroundImage = background_image
    )
}
