package com.dalmuina.network.dto

import com.dalmuina.model.GameDetail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameDetailDto(
    @SerialName("name")val name : String,
    @SerialName("description_raw")val descriptionRaw : String,
    @SerialName("metacritic")val metacritic: Int?,
    @SerialName("website")val website : String,
    @SerialName("background_image")val backgroundImage: String
)

internal fun GameDetailDto.toGameDetail(): GameDetail{
    return GameDetail(
        name = name,
        descriptionRaw = descriptionRaw,
        metacritic = metacritic,
        website = website,
        backgroundImage = backgroundImage
    )
}
