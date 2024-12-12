package com.fueled.technicalchallenge.domain.model

data class Character(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnail: Image,
    val resourceURI: String,
    val events: ResourceItems,
    val urls: List<Url>,
) {
    val defaultImageUrl =
        "${thumbnail.path}/${ImageVariant.PORTRAIT_INCREDIBLE.pathValue}.${thumbnail.extension}"
            .formatUrl()

    val fullImageUrl =
        "${thumbnail.path}${ImageVariant.FULL_SIZE.pathValue}.${thumbnail.extension}"
            .formatUrl()

    companion object {
        val NULL = Character(
            id = -1L,
            name = "",
            description = "",
            thumbnail = Image("", ""),
            resourceURI = "",
            events = ResourceItems(
                available = -1,
                returned = -1,
                collectionURI = "",
                items = listOf(),
            ),
            urls = listOf(),
        )
    }
}

private fun String.formatUrl(): String {
    return when {
        startsWith("https") -> this
        startsWith("http") -> replace("http", "https")
        else -> "https://$this"
    }
}