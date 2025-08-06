package com.developer.rickandmorty.features.domain.usecase

import com.developer.rickandmorty.core.Result
import com.developer.rickandmorty.features.data.model.CharacterDetailModel
import com.developer.rickandmorty.features.domain.repository.RickAndMortyRepository
import javax.inject.Inject

class FavoriteUseCase @Inject constructor(
    private val rickAndMortyRepository: RickAndMortyRepository
) {
    suspend fun toggleFavorite(characterDetailModel: CharacterDetailModel): Result<Boolean> {
        return rickAndMortyRepository.toggleFavorite(characterDetailModel)
    }
}