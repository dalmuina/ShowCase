package com.dalmuina.api_client


import com.dalmuina.model.Game
import com.dalmuina.model.GameDetail
import com.dalmuina.model.GamesResponse
import com.dalmuina.utils.NetworkError
import com.dalmuina.utils.Result


interface ApiClient {
    suspend fun getAllGamesFromApi(): Result<List<Game>, NetworkError>
    suspend fun getAllGamesPagingFromApi(page: Int, pageSize: Int)
    : Result<GamesResponse, NetworkError>
    suspend fun getGameById(id: Int): Result<GameDetail, NetworkError>
    suspend fun getGameByName(name: String?): Result<GameDetail, NetworkError>
}
