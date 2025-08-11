package com.developer.rickandmorty.ui.characterDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import coil.compose.AsyncImage
import com.developer.rickandmorty.R
import com.developer.rickandmorty.core.base.BaseScreen
import com.developer.rickandmorty.features.data.model.CharacterDetailModel
import com.developer.rickandmorty.ui.theme.RickAndMortyTheme
import network.chaintech.sdpcomposemultiplatform.sdp

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
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.sdp)
                .clip(RoundedCornerShape(38.sdp))
                .background(Color.Gray)
        ) {
            AsyncImage(
                model = characterDetailModel.image, contentDescription = null,
                alignment = Alignment.TopCenter,
                contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight(0.55f)
                    .fillMaxSize()


            )
            Spacer(modifier = Modifier.padding(10.sdp))
            Text(
                text = stringResource(id = R.string.name) + characterDetailModel.name,
                modifier = Modifier.padding(start = 5.sdp, top = 5.sdp)
            )
            Text(
                text = stringResource(id = R.string.status) + characterDetailModel.status,
                modifier = Modifier.padding(start = 5.sdp, top = 5.sdp)
            )
            Text(
                stringResource(id = R.string.type) + characterDetailModel.species,
                modifier = Modifier.padding(start = 5.sdp, top = 5.sdp)
            )
            Text(
                stringResource(id = R.string.gender) + characterDetailModel.gender,
                modifier = Modifier.padding(start = 5.sdp, top = 5.sdp)
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