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
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RickAndMortyRepositoryImpl @Inject constructor(
    private val characterLocalDS: CharacterLocalDS,
    private val apiService: RickAndMortyApiService
): RickAndMortyRepository {


    override fun getFavoriteCharacter(): Flow<Result<List<CharacterDetailModel>>> {
        return flow {
            try {
                val favoriteCharacters = characterLocalDS.getFavoriteCharacters()
                if (favoriteCharacters.isEmpty()) {
                    emit(Result.Error("No favorite characters found"))
                } else {
                    emit(Result.Success(favoriteCharacters))
                }

            }catch (e: Exception) {
                emit(Result.Error(e.message ?: "An unknown error occurred"))
            }
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