package com.developer.rickandmorty.ui.episode

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.developer.rickandmorty.core.Result
import com.developer.rickandmorty.core.base.BaseViewModel
import com.developer.rickandmorty.features.data.model.CharacterDetailModel
import com.developer.rickandmorty.features.data.model.EpisodeDetailModel
import com.developer.rickandmorty.features.domain.usecase.EpisodeUseCase
import com.developer.rickandmorty.features.domain.usecase.FavoriteListUseCase
import com.developer.rickandmorty.features.domain.usecase.FavoriteUseCase
import com.developer.rickandmorty.features.domain.usecase.RickAndMortyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EpisodeVM @Inject constructor(
    private val episodeUseCase: EpisodeUseCase
    ) : BaseViewModel() {

        init {
            getFavorites()
        }

    private val _episodes = MutableStateFlow<List<EpisodeDetailModel>>(emptyList())
    val episodes = _episodes.asStateFlow()

    init {
        getFavorites()
    }

    private fun getFavorites() {
        showLoading()
        viewModelScope.launch {
            episodeUseCase.getEpisode().let { result ->
                    when (result) {
                        is Result.Success -> {
                            _episodes.value = result.data ?: emptyList()
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
