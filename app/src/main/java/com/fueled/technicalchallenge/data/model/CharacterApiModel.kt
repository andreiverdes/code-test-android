package com.fueled.technicalchallenge.data.model

import com.squareup.moshi.JsonClass

/**
 * Created by bhavya@fueled.com on 17/10/2024.
 */
@JsonClass(generateAdapter = true)
data class CharacterApiModel(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnail: ImageApiModel,
    val resourceURI: String,
    val comics: ResourceItemsApiModel,
    val series: ResourceItemsApiModel,
    val stories: ResourceItemsApiModel,
    val events: ResourceItemsApiModel,
    val urls: List<UrlApiModel>,
)