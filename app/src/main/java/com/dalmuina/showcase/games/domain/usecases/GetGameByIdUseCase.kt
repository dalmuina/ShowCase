package com.dalmuina.showcase.games.domain.usecases

import com.dalmuina.showcase.games.data.GameRepository
import com.dalmuina.showcase.games.presentation.models.GameDetailUi

class GetGameByIdUseCase (private val repository: GameRepository){

    suspend operator fun invoke(id: Int):GameDetailUi? {
        //return repository.getGameById(id)
        return null
    }
}
