package com.dalmuina.showcase.games.data.network.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GameDto(
    val id:Int,
    val name:String,
    @SerializedName("background_image") val backgroundImage: String
)
