package com.fueled.technicalchallenge.presentation.character.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.fueled.technicalchallenge.ChallengeApplication
import com.fueled.technicalchallenge.data.repository.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(
    private val characterRepository: CharacterRepository,
) : ViewModel() {

    val state = MutableStateFlow(CharacterDetailsState(
        isLoading = true,
    ))


    fun getCharacterDetails(characterId: Int) {
        viewModelScope.launch {
            state.value = CharacterDetailsState(isLoading = true)
            characterRepository. getCharacter(characterId)
                .fold(
                    onSuccess = {
                        state.value = CharacterDetailsState(character = it)
                    },
                    onFailure = {
                        state.value = CharacterDetailsState(error = it.message ?: "")
                    }
                )
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as ChallengeApplication
                CharacterDetailsViewModel(characterRepository = application.characterRepository)
            }
        }
    }
}



