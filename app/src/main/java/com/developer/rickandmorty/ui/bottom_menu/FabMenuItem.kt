package com.developer.rickandmorty.ui.bottom_menu

import androidx.compose.ui.graphics.vector.ImageVector

data class FabMenuItem(
    val title: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)