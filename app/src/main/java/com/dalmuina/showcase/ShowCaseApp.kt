package com.dalmuina.showcase

import android.app.Application
import com.dalmuina.showcase.di.appModule
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@HiltAndroidApp
class ShowCaseApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ShowCaseApp)
            androidLogger()

            modules(appModule)
        }
    }
}
