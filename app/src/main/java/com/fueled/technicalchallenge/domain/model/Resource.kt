package com.fueled.technicalchallenge.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Resource(val resourceURI: String, val name: String, val type: ResourceType?)
