package com.dalmuina.network.di

import org.koin.dsl.module


val networkModule = module{
    includes(ktorModule)
}
