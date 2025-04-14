package com.dalmuina.domain.repository

import com.dalmuina.model.Game
import com.dalmuina.model.GameDetail
import com.dalmuina.utils.NetworkError
import com.dalmuina.utils.Result

interface GameRepository {
    suspend fun getAllGamesFromApi(): Result<List<Game>, NetworkError>
    suspend fun getGameById(id:Int): Result<GameDetail, NetworkError>
    suspend fun getGameByName(name:String?): Result<GameDetail, NetworkError>
}
