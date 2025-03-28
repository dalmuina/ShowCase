package com.dalmuina.core.data.network

import com.dalmuina.core.domain.util.NetworkError
import com.dalmuina.core.domain.util.Result
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import java.nio.channels.UnresolvedAddressException
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(
    execute:() -> HttpResponse
): Result<T, NetworkError> {
    val response = try {
        execute()
    }catch (_: UnresolvedAddressException) {
        return Result.Error(NetworkError.NO_INTERNET)
    } catch (_: SerializationException) {
        return Result.Error(NetworkError.SERIALIZATION)
    } catch (_: Exception){
        coroutineContext.ensureActive()
        return Result.Error(NetworkError.UNKNOWN)
    }

    return responseToResult(response)
}
