package com.dalmuina.showcase.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dalmuina.data.repository.GameRepositoryImpl
import com.dalmuina.domain.GetGameByIdUseCase
import com.dalmuina.model.GameDetail
import com.dalmuina.utils.Result
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UiViewModelTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var repository: GameRepositoryImpl

    @RelaxedMockK
    private lateinit var getGameByIdUseCase: GetGameByIdUseCase

    private lateinit var viewModel: UiViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = UiViewModel(
            repository,
            getGameByIdUseCase,
            testDispatcher
        )
    }

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Test
    fun `Initial state of detail`() = testScope.runTest {
        // When
        val initialState = viewModel.detail.value

        // Then
        assertThat(initialState.isLoading).isFalse()
        assertThat(initialState.gameDetailUi).isNotNull()
    }

    @Test
    fun `Successful game detail loading`() = testScope.runTest {
        // Given
        val gameId = 1
        val mockGame = GameDetail(
            name = "Test Game",
            descriptionRaw = "Test description",
            metacritic = 90,
            website = "https://test.com",
            backgroundImage = "https://test.com/image.jpg"
        )

        coEvery { getGameByIdUseCase(gameId) } returns Result.Success(mockGame)

        // When
        viewModel.onAction(ListAction.OnLoadGameDetail(gameId))
        advanceUntilIdle()

        // Then
        val state = viewModel.detail.value
        assertThat(state.isLoading).isFalse()
        assertThat(state.gameDetailUi).isNotNull()
        assertThat(state.gameDetailUi.name).isEqualTo("Test Game")
    }

    @Test
    fun `Consecutive game detail loading`() = testScope.runTest {
        // Given
        val gameId1 = 1
        val gameId2 = 2
        val mockGame1 = GameDetail(name = "Game 1", descriptionRaw = "desc", metacritic = 45,
            website = "web", backgroundImage = "image")
        val mockGame2 = GameDetail(name = "Game 2", descriptionRaw = "desc", metacritic = 45,
            website = "web", backgroundImage = "image")

        coEvery { getGameByIdUseCase(gameId1) } returns Result.Success(mockGame1)
        coEvery { getGameByIdUseCase(gameId2) } returns Result.Success(mockGame2)

        // When
        viewModel.onAction(ListAction.OnLoadGameDetail(gameId1))
        advanceUntilIdle()
        val state1 = viewModel.detail.value

        viewModel.onAction(ListAction.OnLoadGameDetail(gameId2))
        advanceUntilIdle()
        val state2 = viewModel.detail.value

        // Then
        assertThat(state1.gameDetailUi.name).isEqualTo("Game 1")
        assertThat(state2.gameDetailUi.name).isEqualTo("Game 2")
        assertThat(state2.gameDetailUi.name).isNotEqualTo(state1.gameDetailUi.name)
    }
}

