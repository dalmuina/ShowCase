package com.dalmuina.showcase.games.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class GameDto(
    val id:Int,
    val name:String,
    val background_image: String
)
