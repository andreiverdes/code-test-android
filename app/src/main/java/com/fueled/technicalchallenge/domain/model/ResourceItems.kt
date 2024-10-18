package com.fueled.technicalchallenge.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResourceItems(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<Resource>,
)
