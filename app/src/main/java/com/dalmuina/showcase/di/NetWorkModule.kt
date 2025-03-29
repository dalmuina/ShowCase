package com.dalmuina.showcase.di

import com.dalmuina.showcase.games.data.network.ktor.HttpClientFactory
import io.ktor.client.engine.cio.CIO
import org.koin.dsl.module

val networkModule = module {
    single { HttpClientFactory.create(CIO.create()) }
}
