package com.developer.rickandmorty.ui.bottom_menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.developer.rickandmorty.R
import com.developer.rickandmorty.ui.home.CharacterListVM
import com.developer.rickandmorty.ui.home.HomeScreen

enum class Screen {
    HOME, FAVORITE, EPISODE
}

@Composable
fun MainScreenWithFabMenu(viewModel: CharacterListVM? = null) {
    var currentScreen by remember { mutableStateOf(Screen.HOME) }

    val homeIcon = Icons.Default.Home
    val favoriteIcon = Icons.Default.Favorite
    val episodeIcon = Icons.Default.Person

    val menuItems = listOf(
        FabMenuItem(stringResource(R.string.menu_home), homeIcon) { currentScreen = Screen.HOME },
        FabMenuItem(stringResource(R.string.menu_favorite), favoriteIcon) {
            currentScreen = Screen.FAVORITE
        },
        FabMenuItem(stringResource(R.string.menu_episode), episodeIcon) {
            currentScreen = Screen.EPISODE
        }
    )

    // Get the current selected icon based on the screen
    val selectedIcon = when (currentScreen) {
        Screen.HOME -> homeIcon
        Screen.FAVORITE -> favoriteIcon
        Screen.EPISODE -> episodeIcon
    }

    Scaffold(
        floatingActionButton = {
            ExpandableFabMenu(
                items = menuItems,
                radius = 100.dp,
                selectedIcon = selectedIcon,
            )
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
        ) {
            when (currentScreen) {
                Screen.HOME -> {
                    if (viewModel != null) {
                        HomeScreen(viewModel = viewModel)
                    } else {
                        // Preview fallback
                        Box(modifier = Modifier.fillMaxSize()) {
                            androidx.compose.material3.Text("Ana Ekran İçeriği")
                        }
                    }
                }

                Screen.FAVORITE -> FavoriteScreen()
                Screen.EPISODE -> EpisodeScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FanLikeFabMenuPreview() {
    MainScreenWithFabMenu()
}