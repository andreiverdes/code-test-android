package com.fueled.technicalchallenge.presentation.character.list

import android.text.Editable.Factory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.fueled.technicalchallenge.ChallengeApplication
import com.fueled.technicalchallenge.data.ApiUtils
import com.fueled.technicalchallenge.data.CharactersApi
import com.fueled.technicalchallenge.data.model.CharacterApiModel
import com.fueled.technicalchallenge.data.model.ImageApiModel
import com.fueled.technicalchallenge.data.model.ResourceApiModel
import com.fueled.technicalchallenge.data.model.ResourceItemsApiModel
import com.fueled.technicalchallenge.domain.model.Character
import com.fueled.technicalchallenge.domain.model.Image
import com.fueled.technicalchallenge.domain.model.Resource
import com.fueled.technicalchallenge.domain.model.ResourceItems
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CharacterListViewModel(private val api: CharactersApi) : ViewModel() {

    val state = MutableStateFlow(CharacterListState())

    init {
        getCharacters()
    }

    private fun getCharacters(query: String? = null) {
        viewModelScope.launch {
            val result = fetchCharacters()
            state.value = CharacterListState(characters = result)
        }
    }

    private suspend fun fetchCharacters(nameQuery: String? = null): List<Character> =
        api.getCharacters(
            ts = ApiUtils.currentTimestamp,
            hash = ApiUtils.hash,
            heroNameQuery = nameQuery
        ).results.map {
            it.asDomainCharacter
        }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as ChallengeApplication
                val api = application.api
                CharacterListViewModel(api)
            }
        }
    }
}



