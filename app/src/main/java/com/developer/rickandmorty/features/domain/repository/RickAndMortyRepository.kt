package com.developer.rickandmorty.features.domain.repository

import androidx.paging.PagingData
import com.developer.rickandmorty.core.Result
import com.developer.rickandmorty.features.data.model.CharacterDetailModel
import com.developer.rickandmorty.features.data.model.EpisodeDetailModel
import kotlinx.coroutines.flow.Flow

interface RickAndMortyRepository {
    fun getFavoriteCharacter(): Flow<Result<List<CharacterDetailModel>>>
    suspend fun toggleFavorite(characterModel: CharacterDetailModel): Result<Boolean>
    fun getCharactersPaging(): Flow<PagingData<CharacterDetailModel>>
    suspend fun getEpisode(): Result<List<EpisodeDetailModel>>
}
