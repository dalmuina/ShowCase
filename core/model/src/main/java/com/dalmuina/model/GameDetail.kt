package com.dalmuina.model

data class GameDetail(
    val name : String,
    val descriptionRaw : String,
    val metacritic: Int?,
    val website : String,
    val backgroundImage: String
)
