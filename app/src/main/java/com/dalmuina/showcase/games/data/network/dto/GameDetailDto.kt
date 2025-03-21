package com.dalmuina.showcase.games.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameDetailDto(
    @SerialName("name") val name : String,
    @SerialName("description_raw") val descriptionRaw : String,
    @SerialName("metacritic") val metacritic: Int,
    @SerialName("website") val website : String,
    @SerialName("background_image") val backgroundImage: String
)
