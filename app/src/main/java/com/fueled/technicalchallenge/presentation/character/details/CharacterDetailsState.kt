package com.fueled.technicalchallenge.presentation.character.details

import com.fueled.technicalchallenge.domain.model.Character

data class CharacterDetailsState(
    val isLoading: Boolean = false,
    val character: Character = Character.NULL,
    var error: String = ""
)
