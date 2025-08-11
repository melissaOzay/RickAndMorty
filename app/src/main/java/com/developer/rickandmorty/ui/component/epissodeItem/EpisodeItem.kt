package com.developer.rickandmorty.ui.component.epissodeItem

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.developer.rickandmorty.features.data.model.EpisodeDetailModel
import com.developer.rickandmorty.ui.theme.getMyTypography
import network.chaintech.sdpcomposemultiplatform.sdp

@Composable
fun EpisodeItem(
    episodeDetailModel: EpisodeDetailModel
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 10.sdp)
            .width(270.dp)
            .height(500.dp)
            .clip(RoundedCornerShape(8.sdp))
            .background(color = Color.Green)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f),
                painter = (episodeDetailModel.image?.let { painterResource(id = it) }!!),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
            Text(
                text = episodeDetailModel.name,
                style = getMyTypography().bodyLarge,
                modifier = Modifier
                    .background(Color.Green)
                    .padding(start = 10.sdp, top = 4.sdp)
            )
            Text(
                text = episodeDetailModel.date,
                style = getMyTypography().titleMedium,
                modifier = Modifier
                    .background(Color.Green)
                    .padding(start = 10.sdp, top = 4.dp)
            )
        }
    }


}
