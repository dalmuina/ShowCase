package com.dalmuina.showcase.games.domain.usecases

import com.dalmuina.showcase.core.domain.util.NetworkError
import com.dalmuina.showcase.core.domain.util.Result
import com.dalmuina.showcase.games.data.GameRepository
import com.dalmuina.showcase.games.data.models.Game
import com.dalmuina.showcase.games.presentation.models.GameUi
import javax.inject.Inject

class GetAllGamesUseCase (private val repository: GameRepository){

    suspend operator fun invoke(): Result<List<Game>, NetworkError>? {
        return repository.getAllGamesFromApi()
    }
}
