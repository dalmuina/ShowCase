package com.dalmuina.data.repository

import com.dalmuina.api_client.ApiClient
import com.dalmuina.domain.GameRepository
import com.dalmuina.model.Game
import com.dalmuina.model.GameDetail
import com.dalmuina.model.GamesResponse
import com.dalmuina.utils.NetworkError
import com.dalmuina.utils.Result


class GameRepositoryImpl (private val apiClient: ApiClient): GameRepository {

    override suspend fun getAllGamesFromApi(): Result<List<Game>, NetworkError>{
        return apiClient.getAllGamesFromApi()
    }

    suspend fun getAllGamesPagingFromApi(
        page: Int,
        pageSize: Int
    ): Result<GamesResponse, NetworkError> {
        return apiClient.getAllGamesPagingFromApi(page,pageSize)
    }

   override suspend fun getGameById(id: Int): Result<GameDetail, NetworkError> {
       return apiClient.getGameById(id)
    }

    override suspend fun getGameByName(name: String?): Result<GameDetail, NetworkError> {

        return apiClient.getGameByName(name)
    }

}
