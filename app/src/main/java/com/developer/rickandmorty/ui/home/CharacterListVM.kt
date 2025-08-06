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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CharacterListVM @Inject constructor(
    private val rickAndMortyUseCase: RickAndMortyUseCase,
    private val favoriteUseCase: FavoriteUseCase,
) : BaseViewModel() {

    private val _refreshTrigger = MutableStateFlow(0)

    val characters: Flow<PagingData<CharacterDetailModel>> = _refreshTrigger
        .flatMapLatest {
            rickAndMortyUseCase().cachedIn(viewModelScope)
        }

    private val _characterUpdates = MutableStateFlow<List<CharacterDetailModel>>(emptyList())
    val characterUpdates: Flow<List<CharacterDetailModel>> = _characterUpdates

    fun toggleFavorite(characterDetailModel: CharacterDetailModel) {
        showLoading()
        viewModelScope.launch {
            favoriteUseCase.toggleFavorite(characterDetailModel).let { result ->
                when (result) {
                    is Result.Success -> {
                        hideLoading()
                        val updatedCharacter =
                            characterDetailModel.copy(isFavorite = !characterDetailModel.isFavorite)
                        _characterUpdates.update { currentList ->
                            val filteredList = currentList.filter { it.id != updatedCharacter.id }
                            filteredList + updatedCharacter
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