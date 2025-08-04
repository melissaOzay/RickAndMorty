package com.developer.rickandmorty.domain.repository

import com.developer.rickandmorty.core.Result
import com.developer.rickandmorty.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface RickAndMortyRepository {
    suspend fun getCharacterById(id: Int): Flow<Result<CharacterModel>>
}
