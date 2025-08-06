package com.developer.rickandmorty.features.domain.usecase

import androidx.paging.PagingData
import com.developer.rickandmorty.features.data.model.CharacterDetailModel
import com.developer.rickandmorty.features.domain.repository.RickAndMortyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RickAndMortyUseCase @Inject constructor(
    private val rickAndMortyRepository: RickAndMortyRepository
) {
     operator fun invoke(): Flow<PagingData<CharacterDetailModel>> {
        return rickAndMortyRepository.getCharactersPaging()
    }
}