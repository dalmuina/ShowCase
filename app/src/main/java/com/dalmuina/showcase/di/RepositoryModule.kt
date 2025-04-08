package com.dalmuina.showcase.di

import com.dalmuina.showcase.games.data.ApiClient
import com.dalmuina.showcase.games.data.GameRepositoryImpl
import com.dalmuina.showcase.games.data.network.ktor.KtorApiClient
import com.dalmuina.showcase.games.domain.usecase.GameRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    single<GameRepository> { GameRepositoryImpl(get()) }
    singleOf(::GameRepositoryImpl)
    single<ApiClient> {
        KtorApiClient(
            httpClient = get())
    }
}
