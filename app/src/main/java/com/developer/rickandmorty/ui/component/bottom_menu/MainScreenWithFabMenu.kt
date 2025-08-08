package com.developer.rickandmorty.ui.component.bottom_menu

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.developer.rickandmorty.features.data.model.CharacterDetailModel
import com.developer.rickandmorty.navigation.BottomNavigation
import com.developer.rickandmorty.ui.characterDetail.CharacterDetailScreen
import com.developer.rickandmorty.ui.episode.EpisodeScreen
import com.developer.rickandmorty.ui.episode.EpisodeVM
import com.developer.rickandmorty.ui.favoritesScreen.FavoritesScreen
import com.developer.rickandmorty.ui.favoritesScreen.FavoritesVM
import com.developer.rickandmorty.ui.home.CharacterListVM
import com.developer.rickandmorty.ui.home.HomeScreen
import com.google.gson.Gson

@Composable
fun MainScreenWithFabMenu(
    characterListVM: CharacterListVM? = null,
    favoriteVM: FavoritesVM? = null,
    episodeVM: EpisodeVM? = null,
) {
    val navController = rememberNavController()
    var currentScreen by remember { mutableStateOf("characters") }
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        bottomBar = {
            if (navBackStackEntry?.destination?.route?.contains("character_detail") != true){
                BottomNavigation(navController = navController) { route ->
                    currentScreen = route
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.DarkGray)
        ) {
            NavigationGraph(
                navController = navController,
                characterListVM = characterListVM,
                favoriteVM = favoriteVM,
                episodeVM = episodeVM
            )
        }
    }
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    characterListVM: CharacterListVM?,
    favoriteVM: FavoritesVM?,
    episodeVM: EpisodeVM?
) {
    NavHost(navController = navController, startDestination = "characters") {
        composable("characters") {
            if (characterListVM != null) {
                HomeScreen(viewModel = characterListVM,
                    onCharacterClick = { character ->
                        val characterJson = Gson().toJson(character)
                        val encodedJson = Uri.encode(characterJson)
                        navController.navigate("character_detail/$encodedJson")
                    }
                    )
            } else {
                // Preview fallback
                Box(modifier = Modifier.fillMaxSize()) {
                    androidx.compose.material3.Text("Ana Ekran İçeriği")
                }
            }
        }
        composable("favorites") {
            favoriteVM?.let { FavoritesScreen(it) }
        }
        composable("episodes") {
            episodeVM?.let { EpisodeScreen(it) }
        }
        composable( "character_detail/{characterJson}",
            arguments = listOf(
                navArgument("characterJson") { type = NavType.StringType }
            )) { backStackEntry ->
            val characterJson = backStackEntry.arguments?.getString("characterJson")
            val character = Gson().fromJson(characterJson, CharacterDetailModel::class.java)

            if (character != null) {
                CharacterDetailScreen(characterDetailModel = character)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FanLikeFabMenuPreview() {
    MainScreenWithFabMenu()
}