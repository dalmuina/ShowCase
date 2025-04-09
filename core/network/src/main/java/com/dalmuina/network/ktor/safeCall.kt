package com.dalmuina.network.ktor

import com.dalmuina.utils.NetworkError
import com.dalmuina.utils.Result
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
