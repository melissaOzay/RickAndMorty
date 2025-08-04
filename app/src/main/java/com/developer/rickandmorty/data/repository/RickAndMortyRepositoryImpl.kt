package com.developer.rickandmorty.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.developer.rickandmorty.core.Result
import com.developer.rickandmorty.data.network.RickAndMortyApiService
import com.developer.rickandmorty.domain.datasource.CharactersPagingSource
import com.developer.rickandmorty.domain.mapper.toCharacterModel
import com.developer.rickandmorty.domain.model.CharacterDetailModel
import com.developer.rickandmorty.domain.model.CharacterModel
import com.developer.rickandmorty.domain.repository.RickAndMortyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RickAndMortyRepositoryImpl @Inject constructor(
    private val apiService: RickAndMortyApiService
):RickAndMortyRepository {

    override suspend fun getCharacterById(id: Int): Flow<Result<CharacterModel>> {
       return flow {
           try {
               val response = apiService.getCharacters(id)
                if (response.characterRams.isNotEmpty()) {
                     val characterModel = response.toCharacterModel()
                     emit(Result.Success(characterModel))
                } else {
                     emit(Result.Error("No character found with id: $id"))
                }
           }catch (e: Exception) {
               emit(Result.Error(e.message ?: "An unknown error occurred"))
           }
       }
    }

    override fun getCharactersPaging(): Flow<PagingData<CharacterDetailModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CharactersPagingSource(apiService)
            }
        ).flow
    }


}