package com.dalmuina.showcase.di

import com.dalmuina.showcase.ui.UiViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module{
    single<CoroutineDispatcher> { Dispatchers.IO }
    viewModelOf(::UiViewModel)
}
