package com.dalmuina.showcase.games.domain

import com.dalmuina.showcase.games.data.GameRepository
import com.dalmuina.showcase.games.presentation.models.GameDetailUi
import javax.inject.Inject

class GetGameByIdUseCase @Inject constructor(private val repository: GameRepository){

    suspend operator fun invoke(id: Int):GameDetailUi? {
        return repository.getGameById(id)
    }
}
