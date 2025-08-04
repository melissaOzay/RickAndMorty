package com.developer.rickandmorty.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.developer.rickandmorty.domain.model.CharacterDetailModel

@Composable
fun HomeScreen(
    viewModel: CharacterListVM,
    onCharacterClick: (CharacterDetailModel) -> Unit = {}
) {
    val characters = viewModel.characters.collectAsLazyPagingItems()


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CharacterList(characters)

    }
}

@Composable
fun CharacterList(
    characters: LazyPagingItems<CharacterDetailModel>,
) {
    LazyColumn {
        items(count = characters.itemCount) { index ->
            val character = characters[index]
            if (character != null) {
                CharacterItem(character = character)
            }
        }
    }
}


