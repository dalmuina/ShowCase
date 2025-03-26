package com.dalmuina.showcase.games.presentation.viewmodel

import app.cash.turbine.test
import com.dalmuina.core.domain.util.Result
import com.dalmuina.showcase.di.testDispatcherModule
import com.dalmuina.showcase.games.domain.model.Game
import com.dalmuina.showcase.games.domain.usecase.GetAllGamesUseCase
import com.dalmuina.showcase.games.domain.usecase.GetGameByIdUseCase
import com.dalmuina.showcase.games.domain.usecase.GetGameByNameUseCase
import com.dalmuina.showcase.games.presentation.model.GameUi
import com.dalmuina.showcase.games.presentation.model.toGameUi
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.get
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest

class GamesViewModelTest: KoinTest {


    @get:Rule
    val mockkRule = MockKRule(this)

    @RelaxedMockK
    private lateinit var getAllGamesUseCase: GetAllGamesUseCase

    @RelaxedMockK
    private lateinit var getGameByIdUseCase: GetGameByIdUseCase

    @RelaxedMockK
    private lateinit var getGameByNameUseCase: GetGameByNameUseCase

    private lateinit var viewModel: GamesViewModel

    @Before
    fun setUp() {
        startKoin {
            modules(
                testDispatcherModule,
            )
        }
        viewModel = GamesViewModel(
            dispatchers = get(),
            getAllGamesUseCase,
            getGameByIdUseCase,
            getGameByNameUseCase
        )
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `initial state should be loading with empty data`() = runTest {
        //Test the behavior of `fetchGames` when the useCase returns an empty game list.
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
            val loadingState = awaitItem()
            assertThat(loadingState.games).isEqualTo(mockGames.map { it.toGameUi() })
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `fetchGames game retrieval failure`() {
        // Verify that `fetchGames` handles errors when retrieving games. Check that `isLoading`
        // changes to true, then false, and that a `NetworkErrorEvent` is emitted.
        // TODO implement test
    }

    @Test
    fun `fetchGames multiple calls`() {
        // Test the behavior of calling `fetchGames` multiple times in succession. Verify
        // that previous calls are correctly handled before new calls are processed and the loading state correctly updates.
        // TODO implement test
    }

    @Test
    fun `onAction OnBackButtonClick`() {
        // Test that `onAction` with `OnBackButtonClick` correctly calls the `clean` method.
        // TODO implement test
    }

    @Test
    fun `onAction OnGameClick`() {
        // Test that `onAction` with `OnGameClick` does not change the state or emit an event.
        // TODO implement test
    }

    @Test
    fun `onAction OnLoadGameDetail`() {
        // Test that `onAction` with `OnLoadGameDetail` correctly calls the `loadGameDetail`
        // method with the correct ID.
        // TODO implement test
    }

    @Test
    fun `onAction OnLoadGameDetailSearched`() {
        // Test that `onAction` with `OnLoadGameDetailSearched` correctly calls the
        // `loadGameDetailSearched` method with the correct search string.
        // TODO implement test
    }

    @Test
    fun `onAction NavigateToGame`() {
        // Test that `onAction` with `NavigateToGame` does not change the state or emit an event.
        // TODO implement test
    }

    @Test
    fun `events flow emission`() {
        // Ensure the events flow is emitting events as expected when errors occur, this
        // is an edge case that needs to be cover.
        // TODO implement test
    }

    @Test
    fun `loadGameDetail negative id`() {
        // Test the behavior of `loadGameDetail` when provided with a negative id. Ensure the correct
        // error handling is in place, and a `NetworkErrorEvent` is emitted.
        // TODO implement test
    }

    @Test
    fun `loadGameDetail zero id`() {
        // Test the behavior of `loadGameDetail` when provided with a zero id. Ensure the correct
        // error handling is in place, and a `NetworkErrorEvent` is emitted.
        // TODO implement test
    }


    @Test
    fun `fetchGames after fail`() {
        //Test the behavior of `fetchGames` after a failing fetch.
        // TODO implement test
    }

    @Test
    fun `loadGameDetail successful game retrieval`() {
        // Verify that `loadGameDetail` successfully retrieves a game 
        //by ID and updates the `_detail` state with the game details. 
        //Check that `isLoading` changes to true, then false, and 
        //that the `gameDetailUi` is populated.
        // TODO implement test
    }

    @Test
    fun `loadGameDetail game retrieval failure`() {
        // Verify that `loadGameDetail` handles errors when retrieving 
        //a game by ID. Check that `isLoading` changes to true, 
        //then false, and that a `NetworkErrorEvent` is emitted.
        // TODO implement test
    }

    @Test
    fun `loadGameDetail invalid ID handling`() {
        // Test the behavior of `loadGameDetail` when provided with 
        //an invalid or non-existent game ID. Ensure the correct error 
        //handling is in place, and a `NetworkErrorEvent` is emitted.
        // TODO implement test
    }

    @Test
    fun `loadGameDetail multiple calls`() {
        // Test the behavior of calling `loadGameDetail` multiple 
        //times in succession. Verify that previous calls are 
        //correctly handled before new calls are processed and 
        //the loading state correctly updates.
        // TODO implement test
    }

    @Test
    fun `loadGameDetailSearched successful game retrieval`() {
        // Verify that `loadGameDetailSearched` successfully 
        //retrieves a game by name and updates the `_detail` state 
        //with the game details. Check that `isLoading` changes to 
        //true, then false, and that the `gameDetailUi` is populated.
        // TODO implement test
    }

    @Test
    fun `loadGameDetailSearched game retrieval failure`() {
        // Verify that `loadGameDetailSearched` handles errors when 
        //retrieving a game by name. Check that `isLoading` changes 
        //to true, then false, and that a `NetworkErrorEvent` is emitted.
        // TODO implement test
    }

    @Test
    fun `loadGameDetailSearched empty search string`() {
        // Test the behavior of `loadGameDetailSearched` when 
        //provided with an empty search string. Ensure the correct 
        //error handling or default behavior is implemented.
        // TODO implement test
    }

    @Test
    fun `loadGameDetailSearched whitespace search string`() {
        // Test the behavior of `loadGameDetailSearched` when 
        //provided with a search string that contains only whitespace. 
        //Ensure that the correct behavior is implemented.
        // TODO implement test
    }

    @Test
    fun `loadGameDetailSearched multiple calls`() {
        // Test the behavior of calling `loadGameDetailSearched` 
        //multiple times in succession. Verify that previous calls 
        //are correctly handled before new calls are processed 
        //and the loading state correctly updates.
        // TODO implement test
    }

    @Test
    fun `clean resets detail state`() {
        // Verify that the `clean` method resets the `_detail` state 
        //to its default `GameDetailState`.
        // TODO implement test
    }

    @Test
    fun `clean after successful detail load`() {
        // Test that calling `clean` after a successful call to 
        //`loadGameDetail` or `loadGameDetailSearched` resets the 
        //`_detail` state to its default state.
        // TODO implement test
    }

    @Test
    fun `clean after detail load failure`() {
        // Test that calling `clean` after a failed call to 
        //`loadGameDetail` or `loadGameDetailSearched` resets the 
        //`_detail` state to its default state.
        // TODO implement test
    }

    @Test
    fun `clean multiple calls`() {
        // Test the effect of calling `clean` multiple times in a 
        //row. Verify that no unexpected behavior occurs and the 
        //state is correctly managed.
        // TODO implement test
    }

    @Test
    fun `loadGameDetailSearched special character search string`() {
        // Test the behavior of `loadGameDetailSearched` when 
        //provided with a search string that contains special characters. 
        //Ensure the correct behavior is implemented.
        // TODO implement test
    }
}