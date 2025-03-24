package com.dalmuina.showcase.di

import com.dalmuina.showcase.games.data.GameRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::GameRepository)
}
