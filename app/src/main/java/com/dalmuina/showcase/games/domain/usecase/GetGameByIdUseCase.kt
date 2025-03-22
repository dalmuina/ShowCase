package com.dalmuina.showcase.games.domain.usecase

import com.dalmuina.showcase.core.domain.util.NetworkError
import com.dalmuina.showcase.core.domain.util.Result
import com.dalmuina.showcase.games.data.GameRepository
import com.dalmuina.showcase.games.domain.model.GameDetail

class GetGameByIdUseCase (private val repository: GameRepository){

    suspend operator fun invoke(id: Int): Result<GameDetail, NetworkError> {
        return repository.getGameById(id)
    }
}
