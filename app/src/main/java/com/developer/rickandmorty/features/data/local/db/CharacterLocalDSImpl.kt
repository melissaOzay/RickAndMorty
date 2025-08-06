package com.developer.rickandmorty.features.data.local.db

import com.developer.rickandmorty.features.data.local.dao.CharacterDao
import com.developer.rickandmorty.features.data.local.entity.CharacterEntity
import com.developer.rickandmorty.features.data.model.CharacterDetailModel
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CharacterLocalDSImpl @Inject constructor(
    private val characterDao: CharacterDao
) :CharacterLocalDS{

    override suspend fun toggleFavorite(characterModel: CharacterDetailModel): Boolean {
        try {
            val isFavorite = characterDao.isImageFavorite(characterModel.id)

            return if (isFavorite) {
                characterDao.removeFavorite(characterModel.id)
                false
            } else {
                val entity = CharacterEntity(
                    characterId = characterModel.id,
                    name = characterModel.name,
                    status = characterModel.status,
                    species = characterModel.species,
                    gender = characterModel.gender,
                    image = characterModel.image,
                    isFavorite = true
                )
                characterDao.addFavorite(entity)
                true
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getFavoriteCharacters(): List<CharacterDetailModel> {
        try {
            val favorites = characterDao.getCharacterList()
            return favorites.map { entity ->
                CharacterDetailModel(
                    id = entity.characterId,
                    name = entity.name,
                    status = entity.status,
                    species = entity.species,
                    gender = entity.gender,
                    image = entity.image,
                    isFavorite = entity.isFavorite ?: false
                )
            }
        } catch (e: Exception) {
            return emptyList()
        }
    }
}