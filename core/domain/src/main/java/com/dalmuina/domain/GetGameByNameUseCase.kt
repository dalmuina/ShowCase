package com.dalmuina.domain


import com.dalmuina.model.GameDetail
import com.dalmuina.utils.NetworkError
import com.dalmuina.utils.Result

class GetGameByNameUseCase (private val repository: GameRepository){

    suspend operator fun invoke(name: String?): Result<GameDetail, NetworkError> {
        return repository.getGameByName(name)
    }
}
