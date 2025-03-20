package com.dalmuina.showcase.games.data.network

import com.dalmuina.showcase.games.data.models.Game
import com.dalmuina.showcase.games.data.models.GameDetail
import javax.inject.Inject

class GameService @Inject constructor(private val api:GameApiClient) {

    suspend fun getGames(): List<Game>? {
        val response = api.getGames()
        if (response.isSuccessful){
            return response.body()?.results
        }
        return null
    }

    suspend fun getGameById(id: Int): GameDetail? {
        val response = api.getGameById(id)
        if(response.isSuccessful){
            return response.body()
        }
        return null
    }
}
