package com.dalmuina.showcase.games.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.dalmuina.core.domain.util.onError
import com.dalmuina.core.domain.util.onSuccess
import com.dalmuina.core.presentation.util.NetworkErrorEvent
import com.dalmuina.showcase.games.data.GameRepository
import com.dalmuina.showcase.games.domain.GamesDataSource
import com.dalmuina.showcase.games.domain.usecase.GetAllGamesUseCase
import com.dalmuina.showcase.games.domain.usecase.GetGameByIdUseCase
import com.dalmuina.showcase.games.domain.usecase.GetGameByNameUseCase
import com.dalmuina.showcase.games.presentation.GameListAction
import com.dalmuina.showcase.games.presentation.model.toGameDetailUi
import com.dalmuina.showcase.games.presentation.model.toGameUi
import com.dalmuina.showcase.games.presentation.state.GameDetailState
import com.dalmuina.showcase.games.presentation.state.GameListState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GamesViewModel (
    private val repository: GameRepository,
    private val getAllGamesUseCase: GetAllGamesUseCase,
    private val getGameByIdUseCase: GetGameByIdUseCase,
    private val getGameByNameUseCase: GetGameByNameUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
    //This project has 2 different ways to fetch information
    //for academic reasons
    //Fetch games for the SearchViewPage, Pager for the HomeView
    private val _state = MutableStateFlow(GameListState())
    val state = _state
        .onStart {
            fetchGames()
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            GameListState()
        )

    val gamesPagingFlow = Pager(
        PagingConfig(
            pageSize = 10,
            enablePlaceholders = false
        )
    ) {
        GamesDataSource(repository)
    }.flow
        .map { pagingData ->
            pagingData.map { it.toGameUi() }
        }
        .cachedIn(viewModelScope)

    private val _events  = Channel<NetworkErrorEvent>()
    val events = _events.receiveAsFlow()

    private val _detail = MutableStateFlow(GameDetailState())
    val detail = _detail.asStateFlow()

    fun onAction(action: GameListAction){
        when(action) {
            GameListAction.OnBackButtonClick -> {
                clean()
            }
            is GameListAction.OnGameClick -> Unit
            is GameListAction.OnLoadGameDetail -> {
                loadGameDetail(action.id)
            }
            is GameListAction.OnLoadGameDetailSearched -> {
                loadGameDetailSearched(action.search)
            }
            is GameListAction.NavigateToGame -> Unit
        }
    }

    private fun fetchGames(){
        viewModelScope.launch(dispatcher) {
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
        viewModelScope.launch(dispatcher) {
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

    fun loadGameDetailSearched(search : String){
        viewModelScope.launch(dispatcher) {
            _detail.update { it.copy(
                isLoading = true
            )}
            getGameByNameUseCase(search)
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

