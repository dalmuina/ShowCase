package com.dalmuina.showcase.games.data.network.ktor

import com.dalmuina.core.domain.util.NetworkError
import com.dalmuina.core.domain.util.Result
import com.dalmuina.showcase.games.data.network.dto.GameDto
import com.dalmuina.showcase.games.data.network.dto.GamesResponseDto
import com.dalmuina.showcase.games.domain.model.Game
import com.dalmuina.showcase.utils.data.network.constructUrl
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class KtorApiClientTest {

    @RelaxedMockK
    private lateinit var httpClient: HttpClient

    private lateinit var ktorApiClient: KtorApiClient

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        ktorApiClient = KtorApiClient(httpClient)
    }

    @Test
    fun `Successful retrieval of all games`() = runBlocking {
        // Verify that the function returns a Result.Success containing
        // a list of Game objects when the API call is successful.
        //Given

    }

    @Test
    fun `Empty game list response`() {
        // Test the scenario where the API returns a successful response but with
        // an empty list of games. Check if the function handles this gracefully
        // and returns an empty list within Result.Success.
        // TODO implement test
    }

    @Test
    fun `Network error   No internet connection`() {
        // Simulate a scenario where there is no internet connection
        // (e.g., using a mock HttpClient). Verify that the function returns
        // Result.Error with NetworkError.NO_INTERNET.
        // TODO implement test
    }

    @Test
    fun `Network error   Timeout`() {
        // Simulate a network timeout scenario. Ensure the
        // function returns Result.Error with NetworkError.TIMEOUT.
        // TODO implement test
    }

    @Test
    fun `Network error   Server error  500 `() {
        // Test the scenario where the API returns a server error (e.g., HTTP 500).
        // Check if the function returns Result.Error with
        // NetworkError.SERVER_ERROR and the correct error code.
        // TODO implement test
    }

    @Test
    fun `Network error   Client error  400 `() {
        // Test the scenario where the API returns a client error (e.g., HTTP 400).
        // Verify that the function returns Result.Error with NetworkError.CLIENT_ERROR
        // and the appropriate error code.
        // TODO implement test
    }

    @Test
    fun `Network error   Unauthorized  401 `() {
        // Test the scenario where the API returns an unauthorized error (HTTP 401).
        // Verify that the function returns Result.Error with NetworkError.UNAUTHORIZED
        // and the error code.
        // TODO implement test
    }

    @Test
    fun `Network error   Generic HTTP error`() {
        // Simulate a generic HTTP error with an arbitrary status code (e.g., 404).
        // Ensure the function correctly handles it as a NetworkError with the corresponding code.
        // TODO implement test
    }

    @Test
    fun `JSON parsing error`() {
        // Simulate a scenario where the API returns a response that cannot be parsed
        // into a GamesResponseDto (e.g., invalid JSON). Check if the function handles the
        // JsonParseException and returns Result.Error with NetworkError.UNKNOWN.
        // TODO implement test
    }

    @Test
    fun `Empty API key`() {
        // Provide an empty API key during initialization of KtorApiClient.
        // Verify that the function handles this and either throws an appropriate
        // exception or returns a specific error state.
        // TODO implement test
    }

    @Test
    fun `Invalid API key format`() {
        // Provide an API key with an invalid format (e.g., wrong length, special characters)
        // and ensure the function handles it appropriately
        // (e.g., by returning an error or throwing an exception).
        // TODO implement test
    }

    @Test
    fun `URL construction error`() {
        //  Although constructUrl is not visible, simulate a scenario where
        //  the URL construction fails (if possible within the test setup, e.g., by mocking
        //  constructUrl to throw an exception). Verify that this failure is handled correctly,
        //  possibly with NetworkError.UNKNOWN.
        // TODO implement test
    }

    @Test
    fun `Cancellation job`() {
        // Test the behavior of the function when the coroutine is cancelled while waiting
        // for the API response. Ensure that the cancellation is handled gracefully and
        // does not lead to crashes or unexpected behavior.
        // TODO implement test
    }

    @Test
    fun `Multiple concurrent calls`() {
        // Test the scenario where multiple calls to getAllGamesFromApi are made concurrently.
        // Verify that the function handles concurrent requests correctly and does not
        // introduce race conditions or unexpected side effects.
        // TODO implement test
    }

}
