package com.fueled.technicalchallenge.cache

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.fueled.technicalchallenge.cache.model.CharacterEntity
import com.fueled.technicalchallenge.data.ApiUtils
import com.fueled.technicalchallenge.data.CharactersApi
import com.fueled.technicalchallenge.data.model.CharacterApiModel
import retrofit2.HttpException
import java.io.IOException

private val CharacterApiModel.asEntityCharacter: CharacterEntity
    get() = CharacterEntity(
        id = this.id.toInt(),
        name = this.name,
        description = this.description,
        resourceURI = this.resourceURI,
        fullImageUrl = this.fullImageUrl,
        defaultImageUrl = this.defaultImageUrl,
    )

@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator(
    private val database: CharactersDatabase,
    private val api: CharactersApi,
) : RemoteMediator<Int, CharacterEntity>() {
    private val characterDao = database.characterDao()

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult {
        return try {
            val offset = when (loadType) {
                LoadType.REFRESH -> null
                // In this example, you never need to prepend, since REFRESH
                // will always load the first page in the list. Immediately
                // return, reporting end of pagination.
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = true
                        )

                    // You must explicitly check if the last item is null when
                    // appending, since passing null to networkService is only
                    // valid for initial load. If lastItem is null it means no
                    // items were loaded after the initial REFRESH and there are
                    // no more items to load.

                    val totalItemsLoaded = state.pages.sumOf { it.data.size }
                    totalItemsLoaded
                }
            }

            // Suspending network load via Retrofit. This doesn't need to be
            // wrapped in a withContext(Dispatcher.IO) { ... } block since
            // Retrofit's Coroutine CallAdapter dispatches on a worker
            // thread.
            val response = api.getCharacters(
                limit = 50,
                offset = offset ?: 0,
                ts = ApiUtils.currentTimestamp,
                hash = ApiUtils.hash,
                heroNameQuery = null,
            )

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    characterDao.clearAll()
                }

                // Insert new users into database, which invalidates the
                // current PagingData, allowing Paging to present the updates
                // in the DB.
                characterDao.insertAll(response.results.map {
                    it.asEntityCharacter
                })
            }

            MediatorResult.Success(
                endOfPaginationReached = response.offset + response.count >= response.total
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

}