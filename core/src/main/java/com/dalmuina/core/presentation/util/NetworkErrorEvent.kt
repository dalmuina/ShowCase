package com.dalmuina.core.presentation.util

import com.dalmuina.core.domain.util.NetworkError

sealed interface NetworkErrorEvent {
    data class Error(val error: NetworkError): NetworkErrorEvent
}