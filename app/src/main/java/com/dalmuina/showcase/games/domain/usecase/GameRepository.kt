package com.dalmuina.showcase.games.domain.usecase

import com.dalmuina.core.domain.util.NetworkError
import com.dalmuina.core.domain.util.Result
import com.dalmuina.showcase.games.domain.model.Game
import com.dalmuina.showcase.games.domain.model.GameDetail

interface GameRepository {
    suspend fun getAllGamesFromApi(): Result<List<Game>, NetworkError>
    suspend fun getGameById(id:Int): Result<GameDetail, NetworkError>
    suspend fun getGameByName(name:String?): Result<GameDetail, NetworkError>
}