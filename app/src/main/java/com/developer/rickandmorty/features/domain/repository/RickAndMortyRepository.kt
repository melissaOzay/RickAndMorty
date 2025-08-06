package com.developer.rickandmorty.features.domain.repository

import androidx.paging.PagingData
import com.developer.rickandmorty.core.Result
import com.developer.rickandmorty.features.data.model.CharacterDetailModel
import com.developer.rickandmorty.features.data.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface RickAndMortyRepository {
    suspend fun getCharacterById(id: Int): Result<CharacterModel>
    suspend fun toggleFavorite(characterModel: CharacterDetailModel): Result<Boolean>
    fun getCharactersPaging(): Flow<PagingData<CharacterDetailModel>>
}
