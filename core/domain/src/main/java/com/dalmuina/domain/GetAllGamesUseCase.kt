package com.dalmuina.domain



import com.dalmuina.domain.repository.GameRepository
import com.dalmuina.model.Game
import com.dalmuina.utils.NetworkError
import com.dalmuina.utils.Result

class GetAllGamesUseCase (private val repository: GameRepository){

    suspend operator fun invoke(): Result<List<Game>, NetworkError> {
        return repository.getAllGamesFromApi()
    }
}
