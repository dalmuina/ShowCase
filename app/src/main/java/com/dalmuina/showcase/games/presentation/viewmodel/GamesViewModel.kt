package com.dalmuina.showcase.games.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dalmuina.showcase.core.domain.util.onError
import com.dalmuina.showcase.core.domain.util.onSuccess
import com.dalmuina.showcase.games.domain.usecase.GetAllGamesUseCase
import com.dalmuina.showcase.games.domain.usecase.GetGameByIdUseCase
import com.dalmuina.showcase.games.domain.usecase.GetGameByNameUseCase
import com.dalmuina.showcase.games.presentation.GameListAction
import com.dalmuina.showcase.games.presentation.NetworkErrorEvent
import com.dalmuina.showcase.games.presentation.model.toGameDetailUi
import com.dalmuina.showcase.games.presentation.model.toGameUi
import com.dalmuina.showcase.games.presentation.state.GameDetailState
import com.dalmuina.showcase.games.presentation.state.GameListState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GamesViewModel (
    private val getAllGamesUseCase: GetAllGamesUseCase,
    private val getGameByIdUseCase: GetGameByIdUseCase,
    private val getGameByNameUseCase: GetGameByNameUseCase
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

    private val _events  = Channel<NetworkErrorEvent>()
    val events = _events.receiveAsFlow()

    private val _detail = MutableStateFlow(GameDetailState())
    val detail = _detail.asStateFlow()

    fun onAction(action: GameListAction){
        when(action) {
            GameListAction.OnBackButtonClick -> {
                clean()
            }
            is GameListAction.OnGameClick -> TODO()
            is GameListAction.OnLoadGameDetail -> {
                loadGameDetail(action.id)
            }
        }
    }

    private fun fetchGames(){
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
                }.onError {error ->
                    _state.update { it.copy(
                        isLoading = false
                    )}
                    _events.send(NetworkErrorEvent.Error(error))
                }

        }
    }

    fun loadGameDetail(id : Int){
        viewModelScope.launch {
            _detail.update { it.copy(
                isLoading = true
            )}
            getGameByIdUseCase(id)
                .onSuccess { game->
                    _detail.update {it.copy(
                        isLoading = false,
                        gameDetailUi = game.toGameDetailUi(),
                    )}
                }.onError {error ->
                    _detail.update { it.copy(
                        isLoading = false
                    )}
                    _events.send(NetworkErrorEvent.Error(error))
                }

        }
    }

    fun clean(){
        _detail.value = GameDetailState()
    }
}

