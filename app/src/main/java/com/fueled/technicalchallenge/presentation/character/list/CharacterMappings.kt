package com.fueled.technicalchallenge.presentation.character.list

import com.fueled.technicalchallenge.data.model.CharacterApiModel
import com.fueled.technicalchallenge.data.model.ImageApiModel
import com.fueled.technicalchallenge.data.model.ResourceApiModel
import com.fueled.technicalchallenge.data.model.ResourceItemsApiModel
import com.fueled.technicalchallenge.data.model.UrlApiModel
import com.fueled.technicalchallenge.domain.model.Character
import com.fueled.technicalchallenge.domain.model.Image
import com.fueled.technicalchallenge.domain.model.Resource
import com.fueled.technicalchallenge.domain.model.ResourceItems
import com.fueled.technicalchallenge.domain.model.Url

val CharacterApiModel.asDomainCharacter: Character
    get() = Character(
        id = this.id,
        name = this.name,
        description = this.description,
        thumbnail = this.thumbnail.asDomainThumbnail,
        resourceURI = this.resourceURI,
        events = this.events.asDomainEvents,
        urls = this.urls.map { it.asDomainUrl },
    )

private val ResourceItemsApiModel.asDomainEvents: ResourceItems
    get() = ResourceItems(
        available = this.available,
        returned = this.returned,
        collectionURI = this.collectionURI,
        items = this.items.map { it.domainResource },
    )

private val ImageApiModel.asDomainThumbnail: Image
    get() = Image(
        path = this.path,
        extension = this.extension
    )

private val ResourceApiModel.domainResource: Resource
    get() = Resource(
        resourceURI = this.resourceURI,
        name = this.name,
        type = this.type,
    )

private val UrlApiModel.asDomainUrl: Url
    get() = Url(type, url)
