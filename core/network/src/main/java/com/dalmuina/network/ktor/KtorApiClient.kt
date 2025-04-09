package com.dalmuina.network.ktor

import com.dalmuina.api_client.ApiClient
import com.dalmuina.model.Game
import com.dalmuina.model.GameDetail
import com.dalmuina.model.GamesResponse
import com.dalmuina.network.dto.GameDetailDto
import com.dalmuina.network.dto.GamesResponseDto
import com.dalmuina.network.dto.toGame
import com.dalmuina.network.dto.toGameDetail
import com.dalmuina.network.dto.toGamesResponse
import com.dalmuina.utils.NetworkError
import com.dalmuina.utils.Result
import com.dalmuina.utils.map
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
