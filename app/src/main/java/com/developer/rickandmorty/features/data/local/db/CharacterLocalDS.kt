package com.developer.rickandmorty.features.data.local.db

import com.developer.rickandmorty.features.data.model.CharacterDetailModel
import com.developer.rickandmorty.features.data.model.EpisodeDetailModel

interface CharacterLocalDS {
    suspend fun toggleFavorite(characterModel: CharacterDetailModel): Boolean

    suspend fun getFavoriteCharacters(): List<CharacterDetailModel>

    suspend fun getEpisode(): List<EpisodeDetailModel>

    suspend fun addedEpisode(list: List<EpisodeDetailModel>) :Unit

}