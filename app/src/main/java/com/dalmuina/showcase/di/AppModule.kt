package com.dalmuina.showcase.di

import org.koin.dsl.module


val appModule = module{
    includes(useCaseModule,viewModelModule)
}
