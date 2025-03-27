package com.dalmuina.showcase.games.data

import com.dalmuina.core.data.network.safeCall
import com.dalmuina.core.domain.util.NetworkError
import com.dalmuina.core.domain.util.Result
import com.dalmuina.core.domain.util.map
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

//    suspend fun getAllGamesPagingFromApi(page:Int,pageSize:Int): GamesResponse{
//        return when (val result = safeCall<GamesResponseDto> {
//            httpClient.get(
//                urlString = constructUrl("/games")
//            ) {
//                parameter("key", "37f4482fde834c2eacc917b929b0643d")
//                parameter("page", page)
//                parameter("page_size", pageSize)
//            }
//        }) {
//            is Result.Success -> result.data.toGamesResponse()
//            is Result.Error -> throw when (result.error) {
//                NetworkError.SERIALIZATION -> SerializationException()
//                // handle other errors as needed
//                else -> RuntimeException("Failed to fetch games")
//            }
//        }
//    }

    suspend fun getAllGamesPagingFromApi(
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

    suspend fun getGameByName(name: String?): Result<GameDetail, NetworkError> {

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
