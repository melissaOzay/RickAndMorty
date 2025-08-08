package com.developer.rickandmorty.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.developer.rickandmorty.features.data.model.CharacterDetailModel
import com.developer.rickandmorty.ui.component.characterItem.CharacterItem

@Composable
fun HomeScreen(
    viewModel: CharacterListVM
) {
    val characters = viewModel.getCharacters().collectAsLazyPagingItems()

    Box(
        modifier = Modifier.fillMaxSize()
            .background(Color.DarkGray)
            .padding(top = 10.dp)
        ) {
        CharacterList(
            characters,
            onCharacterFavoriteClick = { character ->
                viewModel.toggleFavorite(character)
            }
        )
    }
    LaunchedEffect(Unit) {
        viewModel.getCharacters()
    }
}

@Composable
fun CharacterList(
    characters: LazyPagingItems<CharacterDetailModel>,
    onCharacterFavoriteClick: (CharacterDetailModel) -> Unit = {},
) {
    LazyColumn {
        items(
            count = characters.itemCount,
            key = { index -> characters[index]?.id ?: index }
        ) { index ->
            characters[index]?.let { character ->
                CharacterItem(
                    character = character,
                    onFavoriteChange = {
                        onCharacterFavoriteClick(character)
                        characters.refresh()
                    }
                )
            }
        }
    }
}


