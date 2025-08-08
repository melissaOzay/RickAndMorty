package com.developer.rickandmorty.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(var title: String, var icon: ImageVector, var path: String) {
    object Home : BottomNavItem("Characters", Icons.Default.Home, "characters")
    object Episodes : BottomNavItem("Episodes", Icons.Default.Person, "episodes")
    object Favorites : BottomNavItem("Favorites", Icons.Default.Favorite, "favorites")
}
