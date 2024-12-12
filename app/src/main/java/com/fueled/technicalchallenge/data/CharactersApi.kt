package com.fueled.technicalchallenge.data

import com.fueled.technicalchallenge.data.ApiConstants.PUBLIC_KEY
import com.fueled.technicalchallenge.data.model.CharacterApiModel
import com.fueled.technicalchallenge.data.model.PageApiModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersApi {

    // TODO this stays

    @GET("characters")
    suspend fun getCharacters(
        @Query("ts") ts: String,
        @Query("hash") hash: String,
        @Query("apikey") apiKey: String = PUBLIC_KEY,
        @Query("nameStartsWith") heroNameQuery: String?,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): PageApiModel<CharacterApiModel>

    @GET("characters/{characterId}")
    suspend fun getCharacterDetails(
        @Path("characterId") characterId: Int,
        @Query("ts") ts: String,
        @Query("hash") hash: String,
        @Query("apikey") apiKey: String = PUBLIC_KEY,
        @Query("nameStartsWith") heroNameQuery: String?,
    ): PageApiModel<CharacterApiModel>

}