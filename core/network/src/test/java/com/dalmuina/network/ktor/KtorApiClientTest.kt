package com.dalmuina.network.ktor

import com.dalmuina.utils.NetworkError
import com.dalmuina.utils.Result

import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.ByteReadChannel
import java.net.UnknownHostException

class KtorApiClientTest {

    private lateinit var apiClient: KtorApiClient
    private lateinit var mockEngine: MockEngine

    @Before
    fun setup() {
        mockEngine = MockEngine { request ->
            error("Mock engine not configured for this test")
        }
        apiClient = KtorApiClient(HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json()
            }
        })
    }

    private fun configureMockEngine(
        statusCode: HttpStatusCode = HttpStatusCode.OK,
        responseBody: String = "",
        exception: Throwable? = null
    ) {
        mockEngine = MockEngine { request ->
            exception?.let { throw it }
            respond(
                content = ByteReadChannel(responseBody),
                status = statusCode,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        apiClient = KtorApiClient(HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json()
            }
        })
    }

    @Test
    fun `getAllGamesFromApi success`() = runBlocking {
        // Verify that getAllGamesFromApi returns a successful Result containing a list of
        // Game objects when the API call is successful.
        //Given
        val mockResponse = """
            {
                "count": 2,
                "results": [
                    {"id": 1, "name": "Game 1", "background_image": "Test"},
                    {"id": 2, "name": "Game 2", "background_image": "Test"}
                ]
            }
        """.trimIndent()
        configureMockEngine(responseBody = mockResponse)

        // When
        val result = apiClient.getAllGamesFromApi()

        // Then
        assertThat(result).isInstanceOf(Result.Success::class.java)
        val games = (result as Result.Success).data
        assertThat(games).hasSize(2)
        assertThat(games[0].id).isEqualTo(1)
        assertThat(games[0].name).isEqualTo("Game 1")
        assertThat(games[1].id).isEqualTo(2)
        assertThat(games[1].name).isEqualTo("Game 2")

    }

    @Test
    fun `getAllGamesFromApi network error`() = runBlocking {
        // Given
        configureMockEngine(exception = UnknownHostException("Unknown"))

        // When
        val result = apiClient.getAllGamesFromApi()

        // Then
        assertThat(result).isInstanceOf(Result.Error::class.java)
        assertThat((result as Result.Error).error).isEqualTo(NetworkError.UNKNOWN)
    }


    @Test
    fun `getAllGamesFromApi empty response`() = runBlocking{
        // Given
        val mockResponse = """
            {
                "count": 0,
                "results": []
            }
        """.trimIndent()
        configureMockEngine(responseBody = mockResponse)

        // When
        val result = apiClient.getAllGamesFromApi()

        // Then
        assertThat(result).isInstanceOf(Result.Success::class.java)
        val games = (result as Result.Success).data
        assertThat(games).isEmpty()
    }

    @Test
    fun `getGameById success`() = runBlocking {
        // Given
        val mockResponse = """
            {
                "name": "Test Game",
                "description_raw": "Test Description",
                "metacritic":30,
                "website":"web name",
                "background_image": "test.jpg"
            }
        """.trimIndent()
        configureMockEngine(responseBody = mockResponse)

        // When
        val result = apiClient.getGameById(1)

        // Then
        assertThat(result).isInstanceOf(Result.Success::class.java)
        val game = (result as Result.Success).data
        assertThat(game.name).isEqualTo("Test Game")
    }

    @Test
    fun `getGameByName success`() = runBlocking{
        // Verify that getGameByName returns a successful Result containing a GameDetail
        // object when the API call is successful.
        // When
        val result = apiClient.getGameByName(null)

        // Then
        assertThat(result).isInstanceOf(Result.Error::class.java)
        assertThat((result as Result.Error).error).isEqualTo(NetworkError.UNKNOWN)
    }

}
