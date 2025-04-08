package com.dalmuina.showcase.games.domain.usecase

import com.dalmuina.core.domain.util.NetworkError
import com.dalmuina.core.domain.util.Result
import com.dalmuina.showcase.games.data.GameRepositoryImpl
import com.dalmuina.showcase.games.domain.model.Game
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertFailsWith

class GetAllGamesUseCaseTest {

    @RelaxedMockK
    private lateinit var repository:  GameRepositoryImpl

    private lateinit var getAllGamesUseCase: GetAllGamesUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getAllGamesUseCase = GetAllGamesUseCase(repository)
    }

    @Test
    fun `Successful API call with non empty game list`() = runBlocking{
        // Test if the use case returns a Success result 
        // containing a list of games when the API call is successful 
        // and returns a non-empty list of games.
        //Given
        val mockGameList = List(100){
            Game(id = it, name = "Name", backgroundImage = "Image")
        }
        val mockResult: Result<List<Game>, NetworkError> = Result.Success(mockGameList)
        coEvery { repository.getAllGamesFromApi() } returns mockResult

        // When
        val result = getAllGamesUseCase()

        // Then
        assert(result == mockResult)

    }

    @Test
    fun `Successful API call with empty game list`() = runBlocking {
        // Test if the use case returns a Success result 
        // containing an empty list of games when the API call is successful 
        // but returns an empty list of games.
        //Given
        val mockGameList = emptyList<Game>()
        val mockResult: Result<List<Game>, NetworkError> = Result.Success(mockGameList)
        coEvery { repository.getAllGamesFromApi() } returns mockResult

        // When
        val result = getAllGamesUseCase()

        // Then
        assert(result == mockResult)
    }

    @Test
    fun `API call returns a network error`() = runBlocking {
        // Test if the use case returns a Failure result 
        // containing the appropriate NetworkError 
        // when the API call fails with a network-related error.
        val mockGameList = emptyList<Game>()
        val mockResult: Result<List<Game>, NetworkError> = Result.Error(NetworkError.NO_INTERNET)
        coEvery { repository.getAllGamesFromApi() } returns mockResult

        // When
        val result = getAllGamesUseCase()

        // Then
        assert(result == mockResult)
    }

    @Test
    fun `Repository throws an exception`() = runBlocking<Unit> {
        // Test if the use case propagates an exception 
        // when the repository throws an unexpected exception during the API call. 
        // This could simulate scenarios like database errors or other unexpected issues.
        // Given
        val exception = RuntimeException("Unexpected error")
        coEvery { repository.getAllGamesFromApi() } throws exception

        // When / Then
        assertFailsWith<RuntimeException> {
            getAllGamesUseCase()
        }
    }

}
