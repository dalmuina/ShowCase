package com.dalmuina.showcase.di

import com.dalmuina.showcase.games.presentation.viewmodel.GamesViewModel
import com.dalmuina.showcase.utils.presentation.DefaultDispatchers
import com.dalmuina.showcase.utils.presentation.DispatcherProvider
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val viewModelModule = module{
    single<DispatcherProvider>{DefaultDispatchers()}
    viewModelOf(::GamesViewModel)
}
