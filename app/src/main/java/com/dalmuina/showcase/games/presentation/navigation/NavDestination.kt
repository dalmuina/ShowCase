package com.dalmuina.showcase.games.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
data class Detail(val id:Int, val name:String?)

