package com.fueled.technicalchallenge.presentation.character_list

import com.fueled.technicalchallenge.domain.model.Character

data class CharacterListState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val error: String = ""
)
