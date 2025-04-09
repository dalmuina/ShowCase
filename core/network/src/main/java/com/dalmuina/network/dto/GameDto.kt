package com.dalmuina.network.dto

import com.dalmuina.model.Game
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameDto(
    @SerialName("id")val id:Int,
    @SerialName("name")val name:String,
    @SerialName("background_image")val backgroundImage: String
)

internal fun GameDto.toGame(): Game {
    return Game(
        id = id,
        name = name,
        backgroundImage = backgroundImage
    )
}
