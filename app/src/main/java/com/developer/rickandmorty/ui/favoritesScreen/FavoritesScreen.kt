package com.developer.rickandmorty.ui.favoritesScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.developer.rickandmorty.core.base.BaseScreen
import com.developer.rickandmorty.features.data.model.CharacterDetailModel
import com.developer.rickandmorty.ui.character.CharacterItem

@SuppressLint("UnrememberedMutableState")
@Composable
fun FavoritesScreen(viewModel: FavoritesVM) {
    BaseScreen(viewModel = viewModel) {
        val favorites by viewModel.favorites.collectAsState(emptyList())
    val toggleFavorite : MutableState<Boolean> = mutableStateOf(false)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        FavoriteList(
            favorites,
            onCharacterFavoriteClick = { character ->
                viewModel.toggleFavorite(character)
                toggleFavorite .value =true
            }
        )
    }
    LaunchedEffect(toggleFavorite) {
        viewModel.getFavorites()
        toggleFavorite.value = false
    }
}
}

@Composable
fun FavoriteList(
    characters: List<CharacterDetailModel>,
    onCharacterFavoriteClick: (CharacterDetailModel) -> Unit = {},
) {
    LazyColumn {
        items(
            count = characters.count(),
            key = { index -> characters[index]?.id ?: index }
        ) { index ->
            characters[index]?.let { character ->
                CharacterItem(
                    character = character,
                    onFavoriteChange = {
                        onCharacterFavoriteClick(character)
                    }
                )
            }
        }
    }
}


