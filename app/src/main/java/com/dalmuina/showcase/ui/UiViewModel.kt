package com.dalmuina.showcase.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.dalmuina.data.repository.GameRepositoryImpl
import com.dalmuina.data.GamesDataSource
import com.dalmuina.domain.GetGameByIdUseCase
import com.dalmuina.model_ui.toGameDetailUi
import com.dalmuina.model_ui.toGameUi
import com.dalmuina.showcase.ui.detailView.GameDetailState
import com.dalmuina.utils.NetworkErrorEvent
import com.dalmuina.utils.onError
import com.dalmuina.utils.onSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class UiViewModel (
    private val repository: GameRepositoryImpl,
    private val getGameByIdUseCase: GetGameByIdUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _filter = MutableStateFlow("")
    val filter = _filter.asStateFlow()

    private val debouncedFilter = _filter
        .debounce(300)
        .distinctUntilChanged()

    val gamesPagingFlow = debouncedFilter
        .flatMapLatest { filterValue ->
            Pager(
                PagingConfig(
                    pageSize = 20,
                    enablePlaceholders = false
                )
            ) {
                GamesDataSource(repository, filterValue)
            }.flow
        }.map { pagingData ->
            pagingData.map { it.toGameUi() }
        }.cachedIn(viewModelScope)

    private val _events  = Channel<NetworkErrorEvent>()
    val events = _events.receiveAsFlow()

    private val _detail = MutableStateFlow(GameDetailState())
    val detail = _detail.asStateFlow()

    fun onAction(action: ListAction){
        when(action) {
            ListAction.OnBackButtonClick -> {
                clean()
            }
            is ListAction.OnFilterChange -> {
                if (_filter.value != action.filter) {
                    _filter.update { action.filter }
                }
            }
            is ListAction.OnLoadGameDetail -> {
                loadGameDetail(action.id)
            }
        }
    }

    private fun loadGameDetail(id : Int){
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
                }.onError { error ->
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
