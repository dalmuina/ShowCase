package com.dalmuina.utils


sealed interface NetworkErrorEvent {
    data class Error(val error: NetworkError): NetworkErrorEvent
}
