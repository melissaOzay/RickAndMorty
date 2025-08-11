package com.developer.rickandmorty.ui.component.favoritebutton

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

@Composable
fun FavoriteButton (
    initialChecked: Boolean = false,
    onFavoriteChange: (Boolean) -> Unit
) {
    IconToggleButton(
        checked = initialChecked,
        onCheckedChange = onFavoriteChange,
    ) {
        Image(
            imageVector = if(initialChecked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = null,
        )
    }
}