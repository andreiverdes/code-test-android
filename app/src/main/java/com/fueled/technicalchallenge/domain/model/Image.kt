package com.fueled.technicalchallenge.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Image(val path: String, val extension: String) {
    val url: (ImageVariant) -> String = { iv ->
        if (iv == ImageVariant.FULL_SIZE) {
            "$path.$extension"
        } else {
            "$path/${iv.pathValue}.$extension"
        }
    }
    val defaultUrl = "$path/${ImageVariant.PORTRAIT_INCREDIBLE.pathValue}.$extension"
        .formatUrl()
}

private fun String.formatUrl(): String {
    return when {
        startsWith("https") -> this
        startsWith("http") -> replace("http", "https")
        else -> "https://$this"
    }
}
