package com.dalmuina.showcase.games.domain.models

import com.dalmuina.showcase.games.data.models.Game

data class Games(
    val count: Int,
    val results: List<Game>
)
