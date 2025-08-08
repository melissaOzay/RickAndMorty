package com.developer.rickandmorty.ui.component.bottom_menu

import androidx.compose.ui.graphics.vector.ImageVector

data class FabMenuItem(
    val title: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)