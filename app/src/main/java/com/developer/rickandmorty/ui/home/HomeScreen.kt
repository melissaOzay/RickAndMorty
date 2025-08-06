package com.developer.rickandmorty.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.developer.rickandmorty.features.data.model.CharacterDetailModel
import com.developer.rickandmorty.ui.character.CharacterItem

@Composable
fun HomeScreen(
    viewModel: CharacterListVM,
    onCharacterClick: (CharacterDetailModel) -> Unit = {}
) {
    val characters = viewModel.characters.collectAsLazyPagingItems()

    val characterUpdates by viewModel.characterUpdates.collectAsState(initial = emptyList())


    LaunchedEffect(characterUpdates) {
        if (characterUpdates.isNotEmpty()) {
            characters.refresh()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CharacterList(
            characters,
            onCharacterFavoriteClick = { character ->
                viewModel.toggleFavorite(character)
            }
        )
    }
}

@Composable
fun CharacterList(
    characters: LazyPagingItems<CharacterDetailModel>,
    onCharacterFavoriteClick: (CharacterDetailModel) -> Unit = {},
) {
    LazyColumn {
        items(count = characters.itemCount) { index ->
            val character = characters[index]
            if (character != null) {
                CharacterItem(
                    character = character,
                    onFavoriteChange = {
                        onCharacterFavoriteClick(character)
                    },
                )
            }
        }
    }
}


