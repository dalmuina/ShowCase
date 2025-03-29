package com.dalmuina.showcase.games.data

import com.dalmuina.core.domain.util.NetworkError
import com.dalmuina.core.domain.util.Result
import com.dalmuina.showcase.games.domain.model.Game
import com.dalmuina.showcase.games.domain.model.GameDetail
import com.dalmuina.showcase.games.domain.model.GamesResponse

interface ApiClient {
    suspend fun getAllGamesFromApi(): Result<List<Game>, NetworkError>
    suspend fun getAllGamesPagingFromApi(page: Int, pageSize: Int)
    : Result<GamesResponse, NetworkError>
    suspend fun getGameById(id: Int): Result<GameDetail, NetworkError>
    suspend fun getGameByName(name: String?): Result<GameDetail, NetworkError>
}
