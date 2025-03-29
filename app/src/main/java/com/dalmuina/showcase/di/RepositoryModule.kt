package com.dalmuina.showcase.di

import com.dalmuina.showcase.games.data.ApiClient
import com.dalmuina.showcase.games.data.GameRepository
import com.dalmuina.showcase.games.data.network.ktor.KtorApiClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::GameRepository)
    single<ApiClient> {
        KtorApiClient(
            httpClient = get(),
            apiKey = "37f4482fde834c2eacc917b929b0643d"
        )
    }
}
