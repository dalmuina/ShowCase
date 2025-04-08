package com.dalmuina.showcase.games.domain.usecase

import com.dalmuina.core.domain.util.NetworkError
import com.dalmuina.core.domain.util.Result
import com.dalmuina.showcase.games.data.GameRepositoryImpl
import com.dalmuina.showcase.games.domain.model.GameDetail
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetGameByIdUseCaseTest {

    @RelaxedMockK
    private lateinit var repository:  GameRepositoryImpl

    private lateinit var getGameByIdUseCase: GetGameByIdUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getGameByIdUseCase = GetGameByIdUseCase(repository)
    }

    @Test
    fun `Successful retrieval of game details`() = runBlocking{
        // Verify that the use case returns a Success result containing the GameDetail 
        // when the repository successfully retrieves the game details for a valid ID.
        //Given
        val mockGameDetail = GameDetail(
            name = "Gta V",
            descriptionRaw = "Description",
            metacritic = 67,
            website = "website url",
            backgroundImage = "background image"
        )
        val mockResult : Result<GameDetail, Nothing> = Result.Success(mockGameDetail)
        coEvery { repository.getGameById(23) } returns mockResult
        //When
        val result = getGameByIdUseCase(23)
        //Then
        assert(result == mockResult)
    }

    @Test
    fun `Repository returns a network error`() = runBlocking{
        // Test that the use case returns a Failure result containing the NetworkError 
        // when the repository encounters a network issue while fetching game details.
        //Given
        val mockResult : Result<Nothing, NetworkError> = Result.Error(NetworkError.NO_INTERNET)
        coEvery { repository.getGameById(23) } returns mockResult
        //When
        val result = getGameByIdUseCase(23)
        //Then
        assert(result == mockResult)
    }

    @Test
    fun `Repository returns a not found error`() = runBlocking{
        // Ensure that the use case returns a Failure result with the appropriate 
        // NetworkError (e.g., NotFoundError) when the repository indicates the game 
        // with the given ID doesn't exist.
        //Given
        val mockResult : Result<Nothing, NetworkError> = Result.Error(NetworkError.UNKNOWN)
        coEvery { repository.getGameById(23) } returns mockResult
        //When
        val result = getGameByIdUseCase(23)
        //Then
        assert(result == mockResult)
    }

    @Test
    fun `Repository returns a server error`() = runBlocking{
        // Confirm that the use case returns a Failure result with a ServerError 
        // when the repository encounters a generic server-side problem.
        //Given
        val mockResult : Result<Nothing, NetworkError> = Result.Error(NetworkError.SERVER_ERROR)
        coEvery { repository.getGameById(23) } returns mockResult
        //When
        val result = getGameByIdUseCase(23)
        //Then
        assert(result == mockResult)
    }

    @Test
    fun `ID is zero`() = runBlocking{
        // Check the behavior of the use case when the game ID is zero.  
        // Depending on the expected behavior, this could return success or a specific error. 
        // Ensure the behavior is consistent with the application's requirements.
        //Given
        val mockGameDetail = GameDetail(
            name = "Gta V",
            descriptionRaw = "Description",
            metacritic = 67,
            website = "website url",
            backgroundImage = "background image"
        )
        val mockResult : Result<GameDetail, Nothing> = Result.Success(mockGameDetail)
        coEvery { repository.getGameById(0) } returns mockResult
        //When
        val result = getGameByIdUseCase(0)
        //Then
        assert(result == mockResult)
    }

    @Test
    fun `ID is negative`() = runBlocking{
        // Test the use case with a negative game ID. This might represent an 
        // invalid ID, and the use case should likely return an error (or handle it 
        // gracefully if negative IDs are valid in the system).
        val mockGameDetail = GameDetail(
            name = "Gta V",
            descriptionRaw = "Description",
            metacritic = 67,
            website = "website url",
            backgroundImage = "background image"
        )
        val mockResult : Result<GameDetail, Nothing> = Result.Success(mockGameDetail)
        coEvery { repository.getGameById(-1) } returns mockResult
        //When
        val result = getGameByIdUseCase(-1)
        //Then
        assert(result == mockResult)
    }

    @Test
    fun `ID is maximum integer value`() = runBlocking{
        // Test with the maximum integer value as the ID. This is a boundary test 
        // to check for any potential integer overflow issues within the use case or 
        // repository interaction.
        val mockGameDetail = GameDetail(
            name = "Gta V",
            descriptionRaw = "Description",
            metacritic = 67,
            website = "website url",
            backgroundImage = "background image"
        )
        val mockResult : Result<GameDetail, Nothing> = Result.Success(mockGameDetail)
        coEvery { repository.getGameById(Int.MAX_VALUE) } returns mockResult
        //When
        val result = getGameByIdUseCase(Int.MAX_VALUE)
        //Then
        assert(result == mockResult)
    }

    @Test
    fun `ID is minimum integer value`() = runBlocking{
        // Test with the minimum integer value as the ID.  Similar to the maximum 
        // integer value test, this checks for integer underflow or other potential issues 
        // at the extreme boundary of the ID range.
        val mockGameDetail = GameDetail(
            name = "Gta V",
            descriptionRaw = "Description",
            metacritic = 67,
            website = "website url",
            backgroundImage = "background image"
        )
        val mockResult : Result<GameDetail, Nothing> = Result.Success(mockGameDetail)
        coEvery { repository.getGameById(Int.MIN_VALUE) } returns mockResult
        //When
        val result = getGameByIdUseCase(Int.MIN_VALUE)
        //Then
        assert(result == mockResult)
    }

}
