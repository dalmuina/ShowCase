package com.dalmuina.data.di

import com.dalmuina.api_client.ApiClient
import com.dalmuina.data.repository.GameRepositoryImpl
import com.dalmuina.domain.GameRepository
import com.dalmuina.network.ktor.KtorApiClient
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
