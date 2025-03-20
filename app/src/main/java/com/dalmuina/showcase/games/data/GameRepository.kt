package com.dalmuina.showcase.games.data

import com.dalmuina.showcase.core.data.network.constructUrl
import com.dalmuina.showcase.core.data.network.safeCall
import com.dalmuina.showcase.core.domain.util.NetworkError
import com.dalmuina.showcase.core.domain.util.Result
import com.dalmuina.showcase.core.domain.util.map
import com.dalmuina.showcase.core.presentation.util.Constants.API_KEY
import com.dalmuina.showcase.core.presentation.util.Constants.ENDPOINT
import com.dalmuina.showcase.games.data.network.dto.GamesResponseDto
import com.dalmuina.showcase.games.domain.models.Game
import com.dalmuina.showcase.games.domain.models.toGame
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class GameRepository (private val httpClient: HttpClient) {

    suspend fun getAllGamesFromApi(): Result<List<Game>, NetworkError>{
        return safeCall<GamesResponseDto> {
            httpClient.get(
                urlString = constructUrl("$ENDPOINT$API_KEY")
            )
        }.map { response ->
            response.results.map {
                it.toGame()
            }
        }
    }

//    suspend fun getGameById(id: Int): GameDetailUi? {
//        val response = api.getGameById(id)
//        return response?.toDomain()
//    }

}
