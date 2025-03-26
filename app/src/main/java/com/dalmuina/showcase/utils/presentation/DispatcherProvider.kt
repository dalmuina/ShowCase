package com.dalmuina.showcase.utils.presentation

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default : CoroutineDispatcher
}
