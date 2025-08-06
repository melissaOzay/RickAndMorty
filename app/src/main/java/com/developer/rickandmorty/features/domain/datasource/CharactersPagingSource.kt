package com.developer.rickandmorty.features.domain.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.developer.rickandmorty.features.data.local.db.CharacterLocalDS
import com.developer.rickandmorty.features.data.model.CharacterDetailModel
import com.developer.rickandmorty.features.data.remote.network.RickAndMortyApiService
import com.developer.rickandmorty.features.domain.mapper.toCharacterModel
import javax.inject.Inject


class CharactersPagingSource @Inject constructor(
    private val apiService: RickAndMortyApiService,
    private val localDS: CharacterLocalDS
) : PagingSource<Int, CharacterDetailModel>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterDetailModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterDetailModel> {
        return try {
            val page = params.key ?: 1

            val response = apiService.getCharacters(pageNumber = page)
            val favoriteCharacters = localDS.getFavoriteCharacters()
            val characterModel = response.toCharacterModel(favoriteCharacters)

            if(response.characterRams != null) {
                LoadResult.Page(
                    data = characterModel.characters,
                    prevKey = if (page == 1) null else page.minus(1),
                    nextKey = if (characterModel.characters.isEmpty()) null else page.plus(1),
                )
            } else {
                LoadResult.Page(
                    data = characterModel.characters ?: emptyList(),
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (response.info?.next == null) null else page + 1
                )
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}