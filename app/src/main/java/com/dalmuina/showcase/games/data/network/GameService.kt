package com.dalmuina.showcase.games.data.network

import com.dalmuina.showcase.games.data.network.dto.GameDetailDto
import com.dalmuina.showcase.games.data.network.dto.GameDto

class GameService (private val api:GameApiClient) {

//    suspend fun getGames(): List<GameDto>? {
//        val response = api.getGames()
//        if (response.isSuccessful){
//            return response.body()?.results
//        }
//        return null
//    }
//
//    suspend fun getGameById(id: Int): GameDetailDto? {
//        val response = api.getGameById(id)
//        if(response.isSuccessful){
//            return response.body()
//        }
//        return null
//    }
}
