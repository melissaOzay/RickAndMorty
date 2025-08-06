package com.developer.rickandmorty.features.domain.mapper

import android.util.Log
import com.developer.rickandmorty.features.data.model.CharacterDetailModel
import com.developer.rickandmorty.features.data.model.CharacterModel
import com.developer.rickandmorty.features.data.remote.models.response.CharacterResponse

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