package com.developer.rickandmorty.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.developer.rickandmorty.ui.component.bottom_menu.MainScreenWithFabMenu
import com.developer.rickandmorty.ui.episode.EpisodeVM
import com.developer.rickandmorty.ui.favoritesScreen.FavoritesVM
import com.developer.rickandmorty.ui.home.CharacterListVM
import com.developer.rickandmorty.ui.theme.RickAndMortyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    
    private val characterListVM: CharacterListVM by viewModels()
    private val favoritesVM: FavoritesVM by viewModels()
    private val episodeVM: EpisodeVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.DarkGray
                ) {
                    MainScreenWithFabMenu(
                        characterListVM = characterListVM, 
                        favoriteVM = favoritesVM, 
                        episodeVM = episodeVM
                    )
                }
            }
        }
    }
}