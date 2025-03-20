package com.dalmuina.showcase.games.data.network

import com.dalmuina.showcase.core.presentation.utils.Constants.API_KEY
import com.dalmuina.showcase.core.presentation.utils.Constants.ENDPOINT
import com.dalmuina.showcase.games.data.models.GameDetail
import com.dalmuina.showcase.games.data.network.dto.GamesResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GameApiClient {

    @GET(ENDPOINT + API_KEY)
    suspend fun getGames(): Response<GamesResponseDto>

    @GET("$ENDPOINT/{id}$API_KEY")
    suspend fun getGameById(@Path(value = "id")id : Int): Response<GameDetail>
}

