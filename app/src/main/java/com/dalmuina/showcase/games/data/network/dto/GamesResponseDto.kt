package com.dalmuina.showcase.games.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class GamesResponseDto(
    val count: Int,
    val results: List<GameDto>
)
