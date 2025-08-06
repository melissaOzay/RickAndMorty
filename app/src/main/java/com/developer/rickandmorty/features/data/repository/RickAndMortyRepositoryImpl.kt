package com.developer.rickandmorty.features.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.developer.rickandmorty.core.Result
import com.developer.rickandmorty.features.data.local.db.CharacterLocalDS
import com.developer.rickandmorty.features.data.model.CharacterDetailModel
import com.developer.rickandmorty.features.data.model.CharacterModel
import com.developer.rickandmorty.features.data.remote.network.RickAndMortyApiService
import com.developer.rickandmorty.features.domain.datasource.CharactersPagingSource
import com.developer.rickandmorty.features.domain.mapper.toCharacterModel
import com.developer.rickandmorty.features.domain.repository.RickAndMortyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RickAndMortyRepositoryImpl @Inject constructor(
    private val characterLocalDS: CharacterLocalDS,
    private val apiService: RickAndMortyApiService
): RickAndMortyRepository {

    override suspend fun getCharacterById(id: Int): Result<CharacterModel> {
        return try {
               val response = apiService.getCharacters(id)
               val favoriteCharacters = characterLocalDS.getFavoriteCharacters()
                if (response.characterRams.isNotEmpty()) {
                     val characterModel = response.toCharacterModel(favoriteCharacters)
                    Result.Success(characterModel)
                } else {
                     Result.Error("No character found with id: $id")
                }
           }catch (e: Exception) {
              Result.Error(e.message ?: "An unknown error occurred")
           }
    }
    
    override suspend fun toggleFavorite(characterModel: CharacterDetailModel): Result<Boolean> {
        return try {
            val isFavorite = characterLocalDS.toggleFavorite(characterModel)
            Result.Success(isFavorite)
        } catch (e: Exception) {
            Result.Error(e.message ?: "An unknown error occurred")
        }
    }

    override fun getCharactersPaging(): Flow<PagingData<CharacterDetailModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CharactersPagingSource(apiService, characterLocalDS)
            }
        ).flow
    }
}