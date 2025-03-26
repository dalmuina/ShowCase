package com.dalmuina.showcase.di

import com.dalmuina.showcase.utils.presentation.DispatcherProvider
import com.dalmuina.showcase.utils.presentation.TestDispatchers
import org.koin.dsl.module

val testDispatcherModule = module {
    single<DispatcherProvider> { TestDispatchers() }
}