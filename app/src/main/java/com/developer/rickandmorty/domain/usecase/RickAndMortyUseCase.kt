package com.developer.rickandmorty.domain.usecase

import com.developer.rickandmorty.domain.repository.RickAndMortyRepository
import javax.inject.Inject

class RickAndMortyUseCase @Inject constructor(
    private val rickAndMortyRepository: RickAndMortyRepository
) {
    suspend operator fun invoke(id: Int) = rickAndMortyRepository.getCharacterById(id)
}