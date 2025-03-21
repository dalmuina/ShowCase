package com.dalmuina.showcase.games.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameDto(
    @SerialName("id")val id:Int,
    @SerialName("name")val name:String,
    @SerialName("background_image")val backgroundImage: String
)
