package com.dalmuina.showcase.games.data.mapper

import com.dalmuina.showcase.games.data.network.dto.GameDto
import com.dalmuina.showcase.games.domain.model.Game

fun GameDto.toGame(): Game{
    return Game(
        id = id,
        name = name,
        backgroundImage = backgroundImage
    )
}
