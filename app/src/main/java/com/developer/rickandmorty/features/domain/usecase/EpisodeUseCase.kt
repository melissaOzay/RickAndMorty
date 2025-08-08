package com.developer.rickandmorty.features.domain.usecase

import com.developer.rickandmorty.core.Result
import com.developer.rickandmorty.features.data.model.EpisodeDetailModel
import com.developer.rickandmorty.features.domain.repository.RickAndMortyRepository
import javax.inject.Inject

class EpisodeUseCase @Inject constructor(
    private val rickAndMortyRepository: RickAndMortyRepository
) {
    suspend fun getEpisode(): Result<List<EpisodeDetailModel>> {
        return rickAndMortyRepository.getEpisode()
    }
}