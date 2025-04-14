package com.dalmuina.showcase.ui

sealed interface ListAction {
    data class OnFilterChange(val filter:String): ListAction
    data class OnLoadGameDetail(val id: Int): ListAction
    object OnBackButtonClick: ListAction
}
