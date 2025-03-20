package com.dalmuina.showcase.games.presentation

import com.dalmuina.showcase.core.domain.util.NetworkError

sealed interface GameListEvent {
    data class Error(val error: NetworkError): GameListEvent
}
