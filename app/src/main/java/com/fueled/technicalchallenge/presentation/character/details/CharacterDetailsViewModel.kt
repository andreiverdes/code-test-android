package com.fueled.technicalchallenge.presentation.character.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.fueled.technicalchallenge.ChallengeApplication
import com.fueled.technicalchallenge.data.ApiUtils
import com.fueled.technicalchallenge.data.CharactersApi
import com.fueled.technicalchallenge.domain.model.Character
import com.fueled.technicalchallenge.presentation.character.list.asDomainCharacter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(private val api: CharactersApi) : ViewModel() {

    val state = MutableStateFlow(CharacterDetailsState(
        isLoading = true,
    ))


    fun getCharacterDetails(characterId: Long) {
        viewModelScope.launch {
            val result = fetchCharacterDetails(characterId)
            state.value = CharacterDetailsState(character = result)
        }
    }

    private suspend fun fetchCharacterDetails(characterId: Long): Character =
        api.getCharacterDetails(
            ts = ApiUtils.currentTimestamp,
            hash = ApiUtils.hash,
            heroNameQuery = null,
            characterId = characterId,
        ).results.map {
            it.asDomainCharacter
        }.firstOrNull() ?: Character.NULL

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as ChallengeApplication
                val api = application.api
                CharacterDetailsViewModel(api)
            }
        }
    }
}



