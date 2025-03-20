package com.dalmuina.showcase.di

import com.dalmuina.showcase.core.presentation.utils.Constants
import com.dalmuina.showcase.games.data.network.GameApiClient
import com.dalmuina.showcase.games.data.network.getClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient())
            .build()
    }

    @Singleton
    @Provides
    fun getGameApiClient(retrofit: Retrofit): GameApiClient {
        return retrofit.create(GameApiClient::class.java)
    }
}
