package com.developer.rickandmorty.ui.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.developer.rickandmorty.core.Result
import com.developer.rickandmorty.core.base.BaseViewModel
import com.developer.rickandmorty.features.data.model.CharacterDetailModel
import com.developer.rickandmorty.features.domain.usecase.FavoriteUseCase
import com.developer.rickandmorty.features.domain.usecase.RickAndMortyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CharacterListVM @Inject constructor(
    private val rickAndMortyUseCase: RickAndMortyUseCase,
    private val favoriteUseCase: FavoriteUseCase,
) : BaseViewModel() {


    fun getCharacters(): Flow<PagingData<CharacterDetailModel>> {
        return rickAndMortyUseCase().cachedIn(viewModelScope)

    }
    init {
        getCharacters()
    }

    fun toggleFavorite(characterDetailModel: CharacterDetailModel) {
        showLoading()
        viewModelScope.launch {
            favoriteUseCase.toggleFavorite(characterDetailModel).let { result ->
                when (result) {
                    is Result.Success -> {
                        hideLoading()
                    }

                    is Result.Error -> {
                        hideLoading()
                    }
                }
            }
        }
    }

}