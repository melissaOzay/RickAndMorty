package com.developer.rickandmorty.ui.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.developer.rickandmorty.core.base.BaseViewModel
import com.developer.rickandmorty.domain.model.CharacterDetailModel
import com.developer.rickandmorty.domain.usecase.RickAndMortyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class CharacterListVM @Inject constructor(
    private val rickAndMortyUseCase: RickAndMortyUseCase
) : BaseViewModel() {


    val characters: Flow<PagingData<CharacterDetailModel>> =
        rickAndMortyUseCase().cachedIn(viewModelScope)
    /*
      fun getCharacterById(id: Int) {
          showLoading()
          viewModelScope.launch {
              rickAndMortyUseCase.invoke(id).collect { result ->
                when (result) {
                 is Result.Success -> {
                      hideLoading()
                      val characterModel: CharacterModel = result.data
                      val characters: List<CharacterDetailModel> = characterModel.characters
                      _characterState.value = characters
                  }
                  is Result.Error -> {
                      hideLoading()
                      val errorMessage = result.message
                      _errorState.value = errorMessage
                  }
              }
          }
      }
      }*/
}