package com.fueled.technicalchallenge.presentation.character.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.cachedIn
import com.fueled.technicalchallenge.ChallengeApplication
import com.fueled.technicalchallenge.data.repository.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow

class CharacterListViewModel(
    private val characterRepository: CharacterRepository,
) : ViewModel() {



    val state = MutableStateFlow(CharacterListState())


    fun getCharacters() = characterRepository.getCharacters().cachedIn(viewModelScope)

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as ChallengeApplication
                CharacterListViewModel(
                    characterRepository = application.characterRepository
                )
            }
        }
    }
}



