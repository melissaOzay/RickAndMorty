package com.developer.rickandmorty.ui.character

import androidx.lifecycle.viewModelScope
import com.developer.rickandmorty.core.Result
import com.developer.rickandmorty.core.base.BaseViewModel
import com.developer.rickandmorty.domain.model.CharacterDetailModel
import com.developer.rickandmorty.domain.model.CharacterModel
import com.developer.rickandmorty.domain.usecase.RickAndMortyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CharacterListVM @Inject constructor(
    private val rickAndMortyUseCase: RickAndMortyUseCase
) : BaseViewModel() {

  private val _characterState = MutableStateFlow<List<CharacterDetailModel>>(emptyList())
  val characterState: StateFlow<List<CharacterDetailModel>> = _characterState
  
  private val _errorState = MutableStateFlow<String?>(null)
  val errorState: StateFlow<String?> = _errorState

  init {
      // Load characters when ViewModel is initialized
      getCharacterById(2)
  }

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
  }
}