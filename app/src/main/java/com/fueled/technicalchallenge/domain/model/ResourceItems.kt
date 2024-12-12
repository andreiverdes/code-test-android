package com.fueled.technicalchallenge.domain.model


data class ResourceItems(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<Resource>,
)
