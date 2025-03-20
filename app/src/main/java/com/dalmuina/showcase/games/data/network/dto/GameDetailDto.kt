package com.dalmuina.showcase.games.data.network.dto

import com.google.gson.annotations.SerializedName

data class GameDetailDto(
    @SerializedName("name") val name : String,
    @SerializedName("description_raw") val descriptionRaw : String,
    @SerializedName("metacritic") val metacritic: Int,
    @SerializedName("website") val website : String,
    @SerializedName("background_image") val backgroundImage: String
)
