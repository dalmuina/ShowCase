package com.dalmuina.showcase.games.data.network.ktor

import com.dalmuina.core.domain.util.NetworkError
import com.dalmuina.core.domain.util.Result
import com.dalmuina.core.domain.util.map
import com.dalmuina.showcase.games.data.ApiClient
import com.dalmuina.showcase.games.data.network.dto.GameDetailDto
import com.dalmuina.showcase.games.data.network.dto.GamesResponseDto
import com.dalmuina.showcase.games.domain.model.Game
import com.dalmuina.showcase.games.domain.model.GameDetail
import com.dalmuina.showcase.games.domain.model.GamesResponse
import com.dalmuina.showcase.games.domain.model.toGame
import com.dalmuina.showcase.games.domain.model.toGameDetail
import com.dalmuina.showcase.games.domain.model.toGamesResponse
import com.dalmuina.showcase.utils.data.network.constructUrl
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class KtorApiClient(
    private val httpClient: HttpClient): ApiClient
{
    override suspend fun getAllGamesFromApi(): Result<List<Game>, NetworkError> {
        return safeCall<GamesResponseDto> {
            httpClient.get(
                urlString = constructUrl("/games")
            ) {
                parameter("key", "37f4482fde834c2eacc917b929b0643d")
            }
        }.map { response ->
            response.results.map {
                it.toGame()
            }
        }
    }

    override suspend fun getAllGamesPagingFromApi(
        page: Int,
        pageSize: Int
    ): Result<GamesResponse, NetworkError> {
        return safeCall<GamesResponseDto> {
            httpClient.get(
                urlString = constructUrl("/games")
            ) {
                parameter("key", "37f4482fde834c2eacc917b929b0643d")
                parameter("page", page)
                parameter("page_size", pageSize)
            }
        }.map { response ->
            response.toGamesResponse()
        }
    }

    override suspend fun getGameById(id: Int): Result<GameDetail, NetworkError> {
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

    override suspend fun getGameByName(name: String?): Result<GameDetail, NetworkError> {
        return if (name!=null) {
            safeCall<GameDetailDto> {
                httpClient.get(
                    urlString = constructUrl("/games/$name")
                ){
                    parameter("key", "37f4482fde834c2eacc917b929b0643d")
                }
            }.map { response ->
                response.toGameDetail()
            }
        }else Result.Error(NetworkError.UNKNOWN)
    }
}
