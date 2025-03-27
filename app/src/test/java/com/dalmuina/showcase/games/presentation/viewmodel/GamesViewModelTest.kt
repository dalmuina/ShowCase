package com.dalmuina.showcase.games.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.dalmuina.core.domain.util.NetworkError
import com.dalmuina.core.domain.util.Result
import com.dalmuina.showcase.games.domain.model.Game
import com.dalmuina.showcase.games.domain.usecase.GetAllGamesUseCase
import com.dalmuina.showcase.games.domain.usecase.GetGameByIdUseCase
import com.dalmuina.showcase.games.domain.usecase.GetGameByNameUseCase
import com.dalmuina.showcase.games.presentation.model.toGameUi
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest


@OptIn(ExperimentalCoroutinesApi::class)
class GamesViewModelTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var getAllGamesUseCase: GetAllGamesUseCase

    @RelaxedMockK
    private lateinit var getGameByIdUseCase: GetGameByIdUseCase

    @RelaxedMockK
    private lateinit var getGameByNameUseCase: GetGameByNameUseCase

    private lateinit var viewModel: GamesViewModel



    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel = GamesViewModel(
            getAllGamesUseCase,
            getGameByIdUseCase,
            getGameByNameUseCase
        )

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state should be loading with empty data`() = runTest {
        //Test the behavior of `fetchGames` when the useCase returns an empty game list.
        //Given
        val mockGames = emptyList<Game>()
        coEvery { getAllGamesUseCase() } returns Result.Success<List<Game>>(mockGames)
        //When
        viewModel.state.test{
            val initialState = awaitItem()
            //Then
            assertThat(initialState.isLoading).isFalse()
            assertThat(initialState.games).isEmpty()
        }
    }

    @Test
    fun `fetchGames successful retrieval`() = runTest{
        // Verify that `fetchGames` successfully retrieves a list of games and updates
        //Given
        val mockGame = Game(1,"GTA V", "GTA V description")
        val mockGames = listOf<Game>(mockGame)
        coEvery { getAllGamesUseCase() } returns Result.Success<List<Game>>(mockGames)
        //When
        viewModel.state.test{
            val initialState = awaitItem()
            //Then
            assertThat(initialState.isLoading).isFalse()
            assertThat(initialState.games).isEmpty()

            assertThat(awaitItem().isLoading).isTrue()

            assertThat(awaitItem().games).isEqualTo(mockGames.map { it.toGameUi() })
            cancelAndIgnoreRemainingEvents()

        }
    }

    @Test
    fun `fetchGames game retrieval failure`() = runTest{
        // Verify that `fetchGames` handles errors when retrieving games.
        //Given
        coEvery { getAllGamesUseCase() } returns Result.Error<NetworkError>(NetworkError.SERVER_ERROR)
        //When
        viewModel.state.test{
            //Then
            assertThat(awaitItem().isLoading).isFalse()
            assertThat(awaitItem().isLoading).isTrue()
            val errorState = awaitItem()
            assertThat(errorState.isLoading).isFalse()
            assertThat(errorState.games).isEmpty()
            cancelAndIgnoreRemainingEvents()
        }
    }

}