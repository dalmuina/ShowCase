package com.dalmuina.network.dto

import com.dalmuina.model.GamesResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GamesResponseDto(
    @SerialName("count")val count: Int,
    @SerialName("results")val results: List<GameDto>
)

internal fun GamesResponseDto.toGamesResponse(): GamesResponse{
    return GamesResponse(
        count = count,
        results = results.map{game->
            game.toGame()
        }
    )
}
