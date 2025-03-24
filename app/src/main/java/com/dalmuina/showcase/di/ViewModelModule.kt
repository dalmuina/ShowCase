package com.dalmuina.showcase.di

import com.dalmuina.showcase.games.presentation.viewmodel.GamesViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module{
    viewModelOf(::GamesViewModel)
}