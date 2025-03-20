package com.dalmuina.showcase.games.data

import com.dalmuina.showcase.games.data.network.GameService
import com.dalmuina.showcase.games.presentation.models.GameDetailUi
import com.dalmuina.showcase.games.presentation.models.GameUi
import com.dalmuina.showcase.games.presentation.models.toDomain
import javax.inject.Inject

class GameRepository  @Inject constructor(private val api: GameService) {

    suspend fun getAllGamesFromApi(): List<GameUi>?{
        val response = api.getGames()
        return response?.map {
            it.toDomain()
        }
    }

    suspend fun getGameById(id: Int): GameDetailUi? {
        val response = api.getGameById(id)
        return response?.toDomain()
    }

}
