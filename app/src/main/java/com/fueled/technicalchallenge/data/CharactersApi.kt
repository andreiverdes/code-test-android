package com.fueled.technicalchallenge.data

import com.fueled.technicalchallenge.data.model.CharacterApiModel
import com.fueled.technicalchallenge.data.model.PageApiModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersApi {

    @GET("characters")
    suspend fun getCharacters(
        @Query("ts") ts: String,
        @Query("hash") hash: String,
        @Query("apikey") apiKey: String = "b90b08ed09de1bfffedb245e6cd7e0ea",
        @Query(ApiConstants.Headers.query) heroNameQuery: String?,
    ): PageApiModel<CharacterApiModel>

    @GET("characters/{id}")
    suspend fun getCharacter(
        @Path("id") id: Long,
    ): PageApiModel<CharacterApiModel>
}