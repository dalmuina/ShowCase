package com.dalmuina.showcase.games.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dalmuina.showcase.core.domain.util.onError
import com.dalmuina.showcase.core.domain.util.onSuccess
import com.dalmuina.showcase.games.domain.usecases.GetAllGamesUseCase
import com.dalmuina.showcase.games.presentation.models.GameDetailUi
import com.dalmuina.showcase.games.presentation.models.toGameUi
import com.dalmuina.showcase.games.presentation.state.GameListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GamesViewModel (private val getAllGamesUseCase: GetAllGamesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(GameListState())
    val state = _state
        .onStart {
            fetchGames()
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            GameListState()
        )

//    private val _games = MutableStateFlow<List<GameUi>>(emptyList())
//    val games = _games.asStateFlow()
//
//    private val _detail = MutableStateFlow(GameDetailUi())
//    val detail: StateFlow<GameDetailUi> = _detail.asStateFlow()


    fun fetchGames(){
        viewModelScope.launch {
            _state.update { it.copy(
                isLoading = true
            )}

            getAllGamesUseCase()
                .onSuccess { games ->
                    _state.update { it.copy(
                        isLoading = false,
                        games = games.map{
                            it.toGameUi()
                        }
                    )}
                }.onError {
                    _state.update { it.copy(
                        isLoading = false
                    )}
                }


//            withContext(Dispatchers.Main) {
//                val result = getAllGamesUseCase() ?: emptyList()
//                _games.value = result
//            }
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
//        _detail.value = GameDetailUi(
//            name = "",
//            descriptionRaw = "",
//            metacritic = 0,
//            website = "",
//            backgroundImage = "",
//        )
    }
}

