package com.dalmuina.showcase.games.domain.usecase

import com.dalmuina.core.domain.util.NetworkError
import com.dalmuina.core.domain.util.Result
import com.dalmuina.showcase.games.domain.model.GameDetail

class GetGameByNameUseCase (private val repository: GameRepository){

    suspend operator fun invoke(name: String?): Result<GameDetail, NetworkError> {
        return repository.getGameByName(name)
    }
}
