package com.fueled.technicalchallenge.domain.model

data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val fullImageUrl: String,
    val defaultImageUrl: String,
) {
    companion object {
        val NULL = Character(
            id = -1,
            name = "",
            description = "",
            fullImageUrl = "",
            defaultImageUrl = "",
        )
    }
}