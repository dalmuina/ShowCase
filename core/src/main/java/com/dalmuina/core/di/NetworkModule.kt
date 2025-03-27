package com.dalmuina.core.di

import com.dalmuina.core.data.network.HttpClientFactory
import io.ktor.client.engine.cio.CIO
import org.koin.dsl.module

val networkModule = module {
    single { HttpClientFactory.create(CIO.create()) }
}
