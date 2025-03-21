package com.dalmuina.showcase.games.domain.usecase

import com.dalmuina.showcase.core.domain.util.NetworkError
import com.dalmuina.showcase.core.domain.util.Result
import com.dalmuina.showcase.games.data.GameRepository
import com.dalmuina.showcase.games.domain.model.Game

class GetAllGamesUseCase (private val repository: GameRepository){

    suspend operator fun invoke(): Result<List<Game>, NetworkError> {
        return repository.getAllGamesFromApi()
    }
}
