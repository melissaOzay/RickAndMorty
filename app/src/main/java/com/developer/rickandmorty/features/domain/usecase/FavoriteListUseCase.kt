package com.developer.rickandmorty.features.domain.usecase

import com.developer.rickandmorty.core.Result
import com.developer.rickandmorty.features.data.model.CharacterDetailModel
import com.developer.rickandmorty.features.domain.repository.RickAndMortyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteListUseCase @Inject constructor(
    private val rickAndMortyRepository: RickAndMortyRepository
) {
      fun getListFavorite(): Flow<Result<List<CharacterDetailModel>>> {
        return rickAndMortyRepository.getFavoriteCharacter()
    }
}