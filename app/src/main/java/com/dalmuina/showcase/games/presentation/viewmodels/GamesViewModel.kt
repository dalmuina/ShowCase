package com.dalmuina.showcase.games.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dalmuina.showcase.games.domain.GetAllGamesUseCase
import com.dalmuina.showcase.games.domain.GetGameByIdUseCase
import com.dalmuina.showcase.games.presentation.models.GameDetailUi
import com.dalmuina.showcase.games.presentation.models.GameUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GamesViewModel (private val getAllGamesUseCase: GetAllGamesUseCase
) : ViewModel() {

    private val _games = MutableStateFlow<List<GameUi>>(emptyList())
    val games = _games.asStateFlow()

    private val _detail = MutableStateFlow(GameDetailUi())
    val detail: StateFlow<GameDetailUi> = _detail.asStateFlow()

    init {
        fetchGames()
    }

    fun fetchGames(){
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                val result = getAllGamesUseCase() ?: emptyList()
                _games.value = result
            }
        }
    }

    fun getGameById(id : Int){
//        viewModelScope.launch {
//            withContext(Dispatchers.Main) {
//                val result = getGameByIdUseCase(id)
//                _detail.value = GameDetailUi(
//                    name = result?.name ?: "",
//                    descriptionRaw = result?.descriptionRaw ?: "",
//                    metacritic = result?.metacritic ?: 0,
//                    website = result?.website ?: "sin web",
//                    backgroundImage = result?.backgroundImage ?: "",
//                )
//            }
//        }
    }

    fun clean(){
        _detail.value = GameDetailUi(
            name = "",
            descriptionRaw = "",
            metacritic = 0,
            website = "",
            backgroundImage = "",
        )
    }
}

