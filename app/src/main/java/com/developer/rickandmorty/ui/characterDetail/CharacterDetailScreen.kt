package com.developer.rickandmorty.ui.characterDetail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.developer.rickandmorty.R
import com.developer.rickandmorty.core.base.BaseScreen
import com.developer.rickandmorty.features.data.model.CharacterDetailModel
import com.developer.rickandmorty.ui.theme.RickAndMortyTheme

@Composable
fun CharacterDetailScreen(characterDetailModel: CharacterDetailModel) {
    BaseScreen(viewModel = null) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray),
            contentAlignment = Alignment.Center
        ) {
            CharacterDetailList(characterDetailModel)
        }
    }
}

@Composable
fun CharacterDetailList(characterDetailModel: CharacterDetailModel) {
    Log.d("deneme", characterDetailModel.toString())
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp)
                .clip(RoundedCornerShape(38.dp))
                .background(Color.Gray)
        ) {
            AsyncImage(
                model = characterDetailModel.image, contentDescription = null,
                alignment = Alignment.TopCenter,
                contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxSize()


            )
            Spacer(modifier = Modifier.padding(10.dp))
            Text(
                text = stringResource(id = R.string.name) + characterDetailModel.name,
                modifier = Modifier.padding(start = 5.dp, top = 5.dp)
            )
            Text(
                text = stringResource(id = R.string.status) + characterDetailModel.status,
                modifier = Modifier.padding(start = 5.dp, top = 5.dp)
            )
            Text(
                stringResource(id = R.string.type) + characterDetailModel.species,
                modifier = Modifier.padding(start = 5.dp, top = 5.dp)
            )
            Text(
                stringResource(id = R.string.gender) + characterDetailModel.gender,
                modifier = Modifier.padding(start = 5.dp, top = 5.dp)
            )

        }


    }
}

@Preview
@Composable
fun Preview() {
    RickAndMortyTheme {
        Surface {
            CharacterDetailList(
                CharacterDetailModel(
                    id = 1,
                    name = "Rick Sanchez",
                    status = "Alive",
                    species = "Human",
                    gender = "elfldfd",
                    image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                )
            )
        }
    }
}