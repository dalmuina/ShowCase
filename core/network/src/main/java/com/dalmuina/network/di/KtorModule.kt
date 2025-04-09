package com.dalmuina.network.di

import com.dalmuina.network.ktor.HttpClientFactory
import io.ktor.client.engine.cio.CIO
import org.koin.dsl.module

val ktorModule = module {
    single{ HttpClientFactory.create(CIO.create()) }
}
