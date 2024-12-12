package com.fueled.technicalchallenge.presentation.character.list

import com.fueled.technicalchallenge.data.model.CharacterApiModel
import com.fueled.technicalchallenge.domain.model.Character

val CharacterApiModel.asDomainModel: Character
    get() = Character(
        id = this.id.toInt(),
        name = this.name,
        description = this.description,
        fullImageUrl = this.fullImageUrl,
        defaultImageUrl = this.defaultImageUrl,
    )