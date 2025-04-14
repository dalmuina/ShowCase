package com.dalmuina.showcase.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
data class Detail(val id:Int, val name:String?)

