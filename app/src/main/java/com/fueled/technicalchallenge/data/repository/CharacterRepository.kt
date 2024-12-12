package com.fueled.technicalchallenge.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.fueled.technicalchallenge.cache.CharacterRemoteMediator
import com.fueled.technicalchallenge.cache.CharactersDatabase
import com.fueled.technicalchallenge.cache.model.CharacterEntity
import com.fueled.technicalchallenge.data.ApiUtils
import com.fueled.technicalchallenge.data.CharactersApi
import com.fueled.technicalchallenge.domain.model.Character
import com.fueled.technicalchallenge.presentation.character.list.asDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val CharacterEntity.asDomainCharacter: Character
    get() = Character(
        id = this.id,
        name = this.name,
        description = this.description,
        fullImageUrl = this.fullImageUrl,
        defaultImageUrl = this.defaultImageUrl,
    )

@OptIn(ExperimentalPagingApi::class)
class CharacterRepository(
    private val database: CharactersDatabase,
    private val api: CharactersApi,
) {

    fun getCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = CharacterRemoteMediator(database, api)
        ) {
            database.characterDao().pagingSource()
        }.flow.map { it.map { it.asDomainCharacter } }
    }

    suspend fun getCharacter(characterId: Int): Result<Character> =
        database.characterDao()
            .getById(characterId)
            ?.asDomainCharacter
            ?.let { Result.success(it) }
            ?: api.getCharacterDetails(
                ts = ApiUtils.currentTimestamp,
                hash = ApiUtils.hash,
                heroNameQuery = null,
                characterId = characterId,
            ).results
                .firstOrNull()
                ?.asDomainModel
                ?.let { Result.success(it) }
            ?: Result.failure(IllegalStateException("Character Not found"))

    companion object {
        const val NETWORK_PAGE_SIZE = 50
    }


}