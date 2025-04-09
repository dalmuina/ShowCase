package com.dalmuina.domain


import com.dalmuina.model.GameDetail
import com.dalmuina.utils.NetworkError
import com.dalmuina.utils.Result

class GetGameByIdUseCase (private val repository: GameRepository){

    suspend operator fun invoke(id: Int): Result<GameDetail, NetworkError> {
        return repository.getGameById(id)
    }
}
