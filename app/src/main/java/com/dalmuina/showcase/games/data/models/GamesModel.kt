package com.dalmuina.showcase.games.data.models

import com.google.gson.annotations.SerializedName

data class Games(
    val count: Int,
    val results: List<Game>
)

data class Game(
    val id:Int,
    val name:String?,
    @SerializedName("background_image") val backgroundImage: String?
)
