package com.dalmuina.showcase.games.data.models

import com.dalmuina.showcase.games.data.network.dto.GameDto
import com.google.gson.annotations.SerializedName

data class Game(
    val id:Int,
    val name:String,
    @SerializedName("background_image") val backgroundImage: String
)

fun GameDto.toGame(): Game {
    return Game(
        id = id,
        name = name,
        backgroundImage = backgroundImage
    )
}