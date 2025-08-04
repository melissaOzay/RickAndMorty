package com.developer.rickandmorty.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.developer.rickandmorty.domain.model.CharacterDetailModel
import com.developer.rickandmorty.ui.character.CharacterListVM

@Composable
fun HomeScreen(
    viewModel: CharacterListVM,
    onCharacterClick: (CharacterDetailModel) -> Unit = {}
) {
    val characters by viewModel.characterState.collectAsState()
    val isLoading by viewModel.loadingState.collectAsState()
    val error by viewModel.errorState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            isLoading -> CircularProgressIndicator()
            error != null -> Text(text = "Hata: $error")
            characters.isEmpty() -> Text(text = "Karakter bulunamadı")
            else -> CharacterList(characters = characters, onCharacterClick = onCharacterClick)
        }
    }
}

@Composable
fun CharacterList(
    characters: List<CharacterDetailModel>,
    onCharacterClick: (CharacterDetailModel) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        items(characters) { character ->
            CharacterItem(character = character, onClick = { onCharacterClick(character) })
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun CharacterItem(
    character: CharacterDetailModel,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = character.name,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Durum: ${character.status}")
            Text(text = "Tür: ${character.species}")
            Text(text = "Cinsiyet: ${character.gender}")
        }
    }
}

