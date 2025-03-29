package com.dalmuina.showcase.games.data

import com.dalmuina.core.domain.util.NetworkError
import com.dalmuina.core.domain.util.Result
import com.dalmuina.showcase.games.domain.model.Game
import com.dalmuina.showcase.games.domain.model.GameDetail
import com.dalmuina.showcase.games.domain.model.GamesResponse

class GameRepository (private val apiClient: ApiClient) {

    suspend fun getAllGamesFromApi(): Result<List<Game>, NetworkError>{
        return apiClient.getAllGamesFromApi()
    }

    suspend fun getAllGamesPagingFromApi(
        page: Int,
        pageSize: Int
    ): Result<GamesResponse, NetworkError> {
        return apiClient.getAllGamesPagingFromApi(page,pageSize)
    }

   suspend fun getGameById(id: Int): Result<GameDetail, NetworkError> {
       return apiClient.getGameById(id)
    }

    suspend fun getGameByName(name: String?): Result<GameDetail, NetworkError> {

        return apiClient.getGameByName(name)
    }

}
