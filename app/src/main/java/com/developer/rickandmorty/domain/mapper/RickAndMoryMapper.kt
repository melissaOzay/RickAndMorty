package com.developer.rickandmorty.domain.mapper

import com.developer.rickandmorty.data.models.response.CharacterResponse
import com.developer.rickandmorty.domain.model.CharacterDetailModel
import com.developer.rickandmorty.domain.model.CharacterModel

 fun CharacterResponse.toCharacterModel(): CharacterModel {
    return CharacterModel(
        characters = this.characterRams.map { character ->
           CharacterDetailModel(
                id = character.id,
                name = character.name,
                status = character.status,
                species = character.species,
               gender = character.gender,
               image = character.image
           )
})
}