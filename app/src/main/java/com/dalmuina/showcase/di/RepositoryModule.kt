package com.dalmuina.showcase.di

import com.dalmuina.showcase.games.data.ApiClient
import com.dalmuina.showcase.games.data.GameRepository
import com.dalmuina.showcase.games.data.network.ktor.KtorApiClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import io.github.cdimascio.dotenv.dotenv

val repositoryModule = module {

    singleOf(::GameRepository)
    single<ApiClient> {
        KtorApiClient(
            httpClient = get())
    }
}
