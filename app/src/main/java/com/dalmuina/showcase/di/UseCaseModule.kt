package com.dalmuina.showcase.di

import com.dalmuina.showcase.games.domain.usecase.GetAllGamesUseCase
import com.dalmuina.showcase.games.domain.usecase.GetGameByIdUseCase
import com.dalmuina.showcase.games.domain.usecase.GetGameByNameUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val useCaseModule = module{
    singleOf(::GetAllGamesUseCase)
    singleOf(::GetGameByIdUseCase)
    singleOf(::GetGameByNameUseCase)
}
