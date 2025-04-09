package com.dalmuina.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dalmuina.model.Game
import com.dalmuina.utils.NetworkError
import com.dalmuina.utils.Result
import kotlinx.serialization.SerializationException
import java.io.IOException

class GamesDataSource(
private val repository: GameRepositoryImpl,
private val filter: String = ""
) : PagingSource<Int, Game>() {

    override fun getRefreshKey(state: PagingState<Int, Game>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {
        return when (val result = repository.getAllGamesPagingFromApi(
            page = params.key ?: 1,
            pageSize = params.loadSize
        )) {
            is com.dalmuina.utils.Result.Success -> {
                val filteredResults = result.data.results.filter {
                    it.name.contains(filter, ignoreCase = true)
                }

                LoadResult.Page(
                    data = filteredResults,
                    prevKey = null, // Only forward paging
                    nextKey = if (result.data.results.isNotEmpty()) {
                        (params.key ?: 1) + 1
                    } else null
                )
            }
            is Result.Error -> {
                LoadResult.Error(
                    when (result.error) {
                        NetworkError.NO_INTERNET -> IOException("No internet")
                        NetworkError.SERIALIZATION -> SerializationException()
                        else -> IOException("Network error")
                    }
                )
            }
        }
    }
}
