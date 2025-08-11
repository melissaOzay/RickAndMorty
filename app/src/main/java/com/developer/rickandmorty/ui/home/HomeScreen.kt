package com.developer.rickandmorty.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.Flow
import com.developer.rickandmorty.core.base.BaseScreen
import com.developer.rickandmorty.features.data.model.CharacterDetailModel
import com.developer.rickandmorty.ui.component.characterItem.CharacterItem
import network.chaintech.sdpcomposemultiplatform.sdp

@Composable
fun HomeScreen(
    viewModel: CharacterListVM,
    onCharacterClick: (CharacterDetailModel) -> Unit = {}
) {
    BaseScreen(viewModel = viewModel) {
        val charactersFlow: Flow<androidx.paging.PagingData<CharacterDetailModel>> = remember { viewModel.getCharacters() }
        val characters = charactersFlow.collectAsLazyPagingItems()
        val isSuccess by viewModel.isSuccess.collectAsState(false)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray)
                .padding(top = 10.sdp)
        ) {
            CharacterList(
                characters,
                onCharacterFavoriteClick = { character ->
                    viewModel.toggleFavorite(character)
                },
                onCharacterClick = {
                    onCharacterClick.invoke(it)
                },
                isSuccess = isSuccess,
                onRefresh = {
                    viewModel.updaterRefresh()
                }
            )
        }
        // getCharacters() zaten Flow döndürüyor; ekstra çağrı gereksiz
    }
}

@Composable
fun CharacterList(
    characters: LazyPagingItems<CharacterDetailModel>,
    onCharacterFavoriteClick: (CharacterDetailModel) -> Unit = {},
    onCharacterClick: (CharacterDetailModel) -> Unit = {},
    isSuccess:Boolean= false,
    onRefresh: () -> Unit = {  }
) {
    val toggledFavorites = remember { mutableStateMapOf<Int, Boolean>() }
    LaunchedEffect(isSuccess) {
        if (isSuccess) {
            characters.refresh()
            onRefresh.invoke()
            toggledFavorites.clear()
        }
    }
    LazyColumn {
        items(
            count = characters.itemCount,
            key = { index -> characters[index]?.id ?: index }
        ) { index ->
            characters[index]?.let { character ->
                val effectiveChecked = toggledFavorites[character.id] ?: character.isFavorite
                CharacterItem(
                    character = character.copy(isFavorite = effectiveChecked),
                    onFavoriteChange = {
                        toggledFavorites[character.id] = it
                        onCharacterFavoriteClick(character)
                    },
                    onCharacterClick = { onCharacterClick(character) }
                )
            }
        }
    }
}


