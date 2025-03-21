package com.dalmuina.showcase.games.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GamesResponseDto(
    @SerialName("count")val count: Int,
    @SerialName("results")val results: List<GameDto>
)
