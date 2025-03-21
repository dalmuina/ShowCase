package com.dalmuina.showcase.di

import com.dalmuina.showcase.core.data.network.HttpClientFactory
import com.dalmuina.showcase.games.data.GameRepository
import com.dalmuina.showcase.games.domain.usecase.GetAllGamesUseCase
import com.dalmuina.showcase.games.domain.usecase.GetGameByIdUseCase
import com.dalmuina.showcase.games.presentation.viewmodel.GamesViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    single { HttpClientFactory.create(CIO.create()) }
    singleOf(::GameRepository)
    singleOf(::GetAllGamesUseCase)
    singleOf(::GetGameByIdUseCase)

    viewModelOf(::GamesViewModel)
}
