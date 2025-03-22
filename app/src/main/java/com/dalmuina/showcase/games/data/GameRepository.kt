package com.dalmuina.showcase.games.data

import com.dalmuina.showcase.core.data.network.constructUrl
import com.dalmuina.showcase.core.data.network.safeCall
import com.dalmuina.showcase.core.domain.util.NetworkError
import com.dalmuina.showcase.core.domain.util.Result
import com.dalmuina.showcase.core.domain.util.map
import com.dalmuina.showcase.games.data.network.dto.GameDetailDto
import com.dalmuina.showcase.games.data.network.dto.GamesResponseDto
import com.dalmuina.showcase.games.domain.model.Game
import com.dalmuina.showcase.games.domain.model.GameDetail
import com.dalmuina.showcase.games.domain.model.toGame
import com.dalmuina.showcase.games.domain.model.toGameDetail
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class GameRepository (private val httpClient: HttpClient) {

    suspend fun getAllGamesFromApi(): Result<List<Game>, NetworkError>{
        return safeCall<GamesResponseDto> {
            httpClient.get(
                urlString = constructUrl("/games")
            ){
                parameter("key", "37f4482fde834c2eacc917b929b0643d")
            }
        }.map { response ->
            response.results.map {
                it.toGame()
            }
        }
    }

   suspend fun getGameById(id: Int): Result<GameDetail, NetworkError> {
       return safeCall<GameDetailDto> {
           httpClient.get(
               urlString = constructUrl("/games/$id")
           ){
               parameter("key", "37f4482fde834c2eacc917b929b0643d")
           }
       }.map { response ->
           response.toGameDetail()
       }
    }

}
