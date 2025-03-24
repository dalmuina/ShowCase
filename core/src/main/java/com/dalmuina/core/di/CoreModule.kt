package com.dalmuina.core.di

import org.koin.dsl.module

val coreModule = module{
    includes(networkModule)
}
