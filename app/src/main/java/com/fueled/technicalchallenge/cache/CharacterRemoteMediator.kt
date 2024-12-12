package com.fueled.technicalchallenge.cache

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.fueled.technicalchallenge.cache.model.CharacterEntity
import com.fueled.technicalchallenge.cache.model.RemoteKey
import com.fueled.technicalchallenge.data.ApiUtils
import com.fueled.technicalchallenge.data.CharactersApi
import com.fueled.technicalchallenge.data.model.CharacterApiModel

private val CharacterApiModel.asEntityModel: CharacterEntity
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

    private var nextPageOffset = 0

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult {
        return try {
            // Determine the offset
            val offset = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKeys = getLastRemoteKey(state)
                        ?: return MediatorResult.Success(endOfPaginationReached = false)
                    remoteKeys.nextKey
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                }
            }

            // API call
            val response = api.getCharacters(
                ts = ApiUtils.currentTimestamp,
                hash = ApiUtils.hash,
                heroNameQuery = null,
                limit = state.config.pageSize,
                offset = offset,
            )

            // Save response data and next key into the database
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.remoteKeyDao().clearRemoteKeys()
                    database.characterDao().clearAll()
                }

                val nextKey = if (response.results.isEmpty()) null else offset + state.config.pageSize
                val keys = response.results.map {
                    RemoteKey(characterId = it.id.toInt(), nextKey = nextKey)
                }
                database.remoteKeyDao().insertAll(keys)
                database.characterDao().insertAll(response.results.map { it.asEntityModel })
            }

            MediatorResult.Success(endOfPaginationReached = response.results.isEmpty())
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
    private suspend fun getLastRemoteKey(state: PagingState<Int, CharacterEntity>): RemoteKey? {
        return state.lastItemOrNull()?.let { character ->
            database.remoteKeyDao().remoteKeysByCharacterId(character.id)
        }
    }
}