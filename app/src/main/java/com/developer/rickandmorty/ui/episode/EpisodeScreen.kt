package com.developer.rickandmorty.ui.episode

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.developer.rickandmorty.core.base.BaseScreen
import com.developer.rickandmorty.features.data.model.EpisodeDetailModel
import com.developer.rickandmorty.ui.component.epissodeItem.EpisodeItem

@Composable
fun EpisodeScreen(
    viewModel: EpisodeVM
) {
    BaseScreen(viewModel = viewModel) {
        val episodes by viewModel.episodes.collectAsState(emptyList())
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray),
            contentAlignment = Alignment.Center
        ) {
            EpisodeList(
                episodes,
            )
        }

    }
}

@Composable
fun EpisodeList(
    episodes: List<EpisodeDetailModel>
) {
    LazyRow {
        items(count = episodes.size) { index ->
            val episode = episodes[index]
            EpisodeItem(episodeDetailModel = episode)
        }
    }
}

@Preview
@Composable
fun EpisodeScreenPreview() {
    val dummyEpisodes = listOf(
        EpisodeDetailModel(id = 1, name = "Pilot", date = "December 2, 2013"),
        EpisodeDetailModel(id = 2, name = "Lawnmower Dog", date = "December 9, 2013"),
        EpisodeDetailModel(id = 3, name = "Anatomy Park", date = "December 16, 2013")
    )
    EpisodeList(episodes = dummyEpisodes)
}



