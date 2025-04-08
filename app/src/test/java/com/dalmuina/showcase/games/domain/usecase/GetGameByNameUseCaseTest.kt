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

class GetGameByNameUseCaseTest {

    @RelaxedMockK
    private lateinit var repository:  GameRepositoryImpl

    private lateinit var getGameByNameUseCase: GetGameByNameUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getGameByNameUseCase = GetGameByNameUseCase(repository)
    }

    @Test
    fun `Successful game retrieval`() = runBlocking {
        // Test if the use case successfully retrieves a GameDetail object
        // when a valid game name is provided and the repository returns a success result.
        //Given
        val mockGameDetail = GameDetail(
            name = "Gta V",
            descriptionRaw = "Description",
            metacritic = 67,
            website = "website url",
            backgroundImage = "background image"
        )
        val mockResult : Result<GameDetail,Nothing> = Result.Success(mockGameDetail)
        coEvery { repository.getGameByName("Gta V") } returns mockResult
        //When
        val result = getGameByNameUseCase("Gta V")
        //Then
        assert(result == mockResult)
    }

    @Test
    fun `Empty game name`() = runBlocking{
        // Test if the use case handles an empty game name correctly,
        // potentially returning an error or a default result.
    //Given
        val mockResult : Result<GameDetail, NetworkError> = Result.Error(
            NetworkError.UNKNOWN)
        coEvery { repository.getGameByName("") } returns mockResult
    //When
        val result = getGameByNameUseCase("")
    //Then
        assert(result == mockResult)
    }

    @Test
    fun `Game name with special characters`() = runBlocking{
        // Test if the use case can handle game names that include
        // special characters (e.g., !@#$%^&*()).
        //Given
        val mockGameDetail = GameDetail(
            name = "Gta V: Special Edition!@#",
            descriptionRaw = "Description",
            metacritic = 67,
            website = "website url",
            backgroundImage = "background image"
        )
        val mockResult : Result<GameDetail, Nothing> = Result.Success(mockGameDetail)
        coEvery { repository.getGameByName("GTA V: Special Edition!@#") } returns mockResult
        //When
        val result = getGameByNameUseCase("GTA V: Special Edition!@#")
        //Then
        assert(result == mockResult)
    }

    @Test
    fun `Game name with spaces`() = runBlocking{
        // Test if the use case correctly handles game names that include spaces.
        // Given
        val mockGameDetail = GameDetail(
            name = "GTA V Special Edition",
            descriptionRaw = "Description",
            metacritic = 67,
            website = "website url",
            backgroundImage = "background image"
        )
        val mockResult: Result<GameDetail, NetworkError> = Result.Success(mockGameDetail)
        coEvery { repository.getGameByName("GTA V Special Edition") } returns mockResult

        // When
        val result = getGameByNameUseCase("GTA V Special Edition")

        // Then
        assert(result == mockResult)
    }

    @Test
    fun `Null game name`() = runBlocking{
        // Test if the use case handles a null game name, ensuring it doesn't
        // crash or throw unexpected exceptions.
        // This might throw an exception or return an error.
        // Given
        val mockResult: Result<GameDetail, NetworkError> = Result.Error(NetworkError.UNKNOWN)
        coEvery { repository.getGameByName(null) } returns mockResult

        // When
        val result = getGameByNameUseCase(null)

        // Then
        assert(result == mockResult)
    }

    @Test
    fun `Repository network error`() = runBlocking{
        // Test if the use case correctly propagates a NetworkError from the repository
        // when a network issue occurs.
        // Given
        val mockResult: Result<GameDetail, NetworkError> = Result.Error(
            NetworkError.NO_INTERNET)
        coEvery { repository.getGameByName("Gta V") } returns mockResult

        // When
        val result = getGameByNameUseCase("Gta V")

        // Then
        assert(result == mockResult)
    }

    @Test
    fun `Repository data error`() = runBlocking{
        // Test if the use case correctly propagates a NetworkError from the repository
        // when it indicates a data issue.
        // Given
        val mockResult: Result<GameDetail, NetworkError> = Result.Error(
            NetworkError.SERIALIZATION)
        coEvery { repository.getGameByName("Gta V") } returns mockResult

        // When
        val result = getGameByNameUseCase("Gta V")

        // Then
        assert(result == mockResult)
    }

    @Test
    fun `Game not found`() = runBlocking{
        // Test if the use case correctly handles the scenario where the repository indicates
        // that a game with the given name was not found, potentially returning a specific error.
        // Given
        val mockResult: Result<GameDetail, NetworkError> = Result.Error(
            NetworkError.UNKNOWN)
        coEvery { repository.getGameByName("Unknown Game") } returns mockResult

        // When
        val result = getGameByNameUseCase("Unknown Game")

        // Then
        assert(result == mockResult)
    }

    @Test
    fun `Extremely long game name`() = runBlocking{
        // Test if the use case can handle an extremely long game name, testing for potential
        // buffer overflow or performance issues.
        // Given
        val longName = "a".repeat(10000)
        val mockResult: Result<GameDetail, NetworkError> = Result.Error(
            NetworkError.UNKNOWN)
        coEvery { repository.getGameByName(longName) } returns mockResult

        // When
        val result = getGameByNameUseCase(longName)

        // Then
        assert(result == mockResult)
    }

    @Test
    fun `Different casing for game names - case insensitive`() = runBlocking {
        // Given
        val mockGameDetail = GameDetail(
            name = "Gta V",
            descriptionRaw = "Description",
            metacritic = 67,
            website = "website url",
            backgroundImage = "background image"
        )
        val mockResult: Result<GameDetail, NetworkError> = Result.Success(mockGameDetail)

        // Mock the repository to return the same result for any casing variation
        coEvery { repository.getGameByName(any()) } returns mockResult

        // When
        val result1 = getGameByNameUseCase("Gta V")
        val result2 = getGameByNameUseCase("gta v")
        val result3 = getGameByNameUseCase("GTA V")

        // Then
        assert(result1 == mockResult)
        assert(result2 == mockResult)
        assert(result3 == mockResult)
    }


}
