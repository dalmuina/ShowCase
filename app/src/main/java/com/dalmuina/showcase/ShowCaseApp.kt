package com.dalmuina.showcase

import android.app.Application
import com.dalmuina.data.di.dataModule
import com.dalmuina.network.di.networkModule
import com.dalmuina.showcase.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ShowCaseApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ShowCaseApp)
            androidLogger()

            modules(appModule, networkModule, dataModule)
        }
    }
}
