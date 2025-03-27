package com.dalmuina.showcase.di

import com.dalmuina.showcase.games.presentation.viewmodel.GamesViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module{
    single<CoroutineDispatcher> { Dispatchers.IO }
    viewModelOf(::GamesViewModel)
}
