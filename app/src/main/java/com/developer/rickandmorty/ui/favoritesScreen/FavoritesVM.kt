package com.developer.rickandmorty.ui.favoritesScreen

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.developer.rickandmorty.core.Result
import com.developer.rickandmorty.core.base.BaseViewModel
import com.developer.rickandmorty.features.data.model.CharacterDetailModel
import com.developer.rickandmorty.features.domain.usecase.FavoriteListUseCase
import com.developer.rickandmorty.features.domain.usecase.FavoriteUseCase
import com.developer.rickandmorty.features.domain.usecase.RickAndMortyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoritesVM @Inject constructor(
    private val favoriteListUseCase: FavoriteListUseCase,
    private val favoriteUseCase: FavoriteUseCase,
    ) : BaseViewModel() {

        init {
            getFavorites()
        }

    private val _favorites = MutableStateFlow<List<CharacterDetailModel>>(emptyList())
    val favorites = _favorites.asSharedFlow()

    fun getFavorites() {
        showLoading()
        viewModelScope.launch {
            favoriteListUseCase.getListFavorite().let { result ->
                result.collect { result ->
                    when (result) {
                        is Result.Success -> {
                            _favorites.value = result.data ?: emptyList()
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
    fun toggleFavorite(characterDetailModel: CharacterDetailModel) {
        showLoading()
        viewModelScope.launch {
            favoriteUseCase.toggleFavorite(characterDetailModel).let { result ->
                when (result) {
                    is Result.Success -> {
                        hideLoading()
                        _favorites.value = _favorites.value.map {
                            if (it.id == characterDetailModel.id) it.copy(isFavorite = !it.isFavorite) else it
                        }
                    }

                    is Result.Error -> {
                        hideLoading()
                    }
                }
            }
        }
    }

}