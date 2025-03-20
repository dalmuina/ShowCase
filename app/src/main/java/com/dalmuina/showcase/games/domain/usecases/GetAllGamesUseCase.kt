package com.dalmuina.showcase.games.domain

import com.dalmuina.showcase.games.data.GameRepository
import com.dalmuina.showcase.games.presentation.models.GameUi
import javax.inject.Inject

class GetAllGamesUseCase @Inject constructor(private val repository: GameRepository){

    suspend operator fun invoke():List<GameUi>? {
        return repository.getAllGamesFromApi()
    }
}
