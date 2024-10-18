package com.fueled.technicalchallenge.domain.repository

import com.fueled.technicalchallenge.common.Resource
import com.fueled.technicalchallenge.data.ApiUtils
import com.fueled.technicalchallenge.data.CharactersApi
import com.fueled.technicalchallenge.domain.model.Character
import com.fueled.technicalchallenge.domain.model.Page
import com.fueled.technicalchallenge.domain.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class CharacterRepository @Inject constructor(
    private val api: CharactersApi
) {

    fun getCharacters(nameQuery: String? = null): Flow<Resource<Page<Character>>> = flow {
        try {
            emit(Resource.Loading())
            val characters = fetchCharacters(nameQuery).results.map { it.toEntity() }
            emit(Resource.Success(Page(characters)))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

    fun getCharacter(id: Long): Flow<Resource<Character>> = flow {
        try {
            emit(Resource.Loading())
            fetchCharacter(id).results.firstOrNull()?.let {
                emit(Resource.Success(it.toEntity()))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

    private suspend fun fetchCharacters(nameQuery: String?) = api.getCharacters(
        ts = ApiUtils.currentTimestamp,
        hash = ApiUtils.hash,
        heroNameQuery = nameQuery
    )

    private suspend fun fetchCharacter(id: Long) = api.getCharacter(id)
}