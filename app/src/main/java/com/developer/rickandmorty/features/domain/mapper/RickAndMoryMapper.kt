package com.developer.rickandmorty.features.domain.mapper

import com.developer.rickandmorty.features.data.model.CharacterDetailModel
import com.developer.rickandmorty.features.data.model.CharacterModel
import com.developer.rickandmorty.features.data.model.EpisodeDetailModel
import com.developer.rickandmorty.features.data.remote.models.response.CharacterResponse
import com.developer.rickandmorty.features.data.remote.models.response.EpisodeDetailResponse

fun CharacterResponse.toCharacterModel(list: List<CharacterDetailModel>): CharacterModel {
    return CharacterModel(
        characters = this.characterRams.map { character ->
            val isFavorite = list.any { it.id == character.id }

            CharacterDetailModel(
                id = character.id,
                name = character.name,
                status = character.status,
                species = character.species,
                gender = character.gender,
                image = character.image,
                isFavorite = isFavorite
            )
        })
}
fun EpisodeDetailResponse.toEpisodeModel(): EpisodeDetailModel {
    return EpisodeDetailModel(
        id = this.id,
        name = this.name,
        date = this.date
    )
}