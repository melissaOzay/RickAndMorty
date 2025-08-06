package com.developer.rickandmorty.ui.favoritebutton

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun FavoriteButton (
    initialChecked: Boolean = false,
    onFavoriteChange: (Boolean) -> Unit
) {
    var isFavorite by remember { mutableStateOf(initialChecked) }
    
    // Update the local state when initialChecked changes
    LaunchedEffect(initialChecked) {
        isFavorite = initialChecked
    }

    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = {
            isFavorite = it
            onFavoriteChange(it)
        },
    ) {
        Image(
            imageVector = if(isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = null,
        )
    }
}