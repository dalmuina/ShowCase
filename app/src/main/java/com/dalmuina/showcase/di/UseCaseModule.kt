package com.dalmuina.showcase.di


import com.dalmuina.domain.GetAllGamesUseCase
import com.dalmuina.domain.GetGameByIdUseCase
import com.dalmuina.domain.GetGameByNameUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val useCaseModule = module{
    singleOf(::GetAllGamesUseCase)
    singleOf(::GetGameByIdUseCase)
    singleOf(::GetGameByNameUseCase)
}
