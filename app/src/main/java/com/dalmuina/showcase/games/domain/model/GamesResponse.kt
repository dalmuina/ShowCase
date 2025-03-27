package com.dalmuina.showcase.games.domain.model

import com.dalmuina.showcase.games.data.network.dto.GamesResponseDto

data class GamesResponse(
    val count: Int,
    val results: List<Game>
)

fun GamesResponseDto.toGamesResponse(): GamesResponse{
    return GamesResponse(
        count = count,
        results = results.map{game->
            game.toGame()
        }
    )
}
