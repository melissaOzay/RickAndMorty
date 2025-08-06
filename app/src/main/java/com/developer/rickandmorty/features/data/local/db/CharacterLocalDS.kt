package com.developer.rickandmorty.features.data.local.db

import com.developer.rickandmorty.features.data.model.CharacterDetailModel

interface CharacterLocalDS {
    suspend fun toggleFavorite(characterModel: CharacterDetailModel): Boolean

    suspend fun getFavoriteCharacters(): List<CharacterDetailModel>

}