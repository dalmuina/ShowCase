package com.dalmuina.showcase.games.domain.usecase

import com.dalmuina.showcase.games.data.GameRepository
import com.dalmuina.showcase.games.presentation.model.GameDetailUi

class GetGameByIdUseCase (private val repository: GameRepository){

    suspend operator fun invoke(id: Int):GameDetailUi? {
        //return repository.getGameById(id)
        return null
    }
}
