package com.fueled.technicalchallenge.presentation.character.list

import com.fueled.technicalchallenge.domain.model.Character

data class CharacterListState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    var error: String = ""
)
